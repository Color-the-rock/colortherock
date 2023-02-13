import React, { useEffect, useState } from "react";
import { useLinkClickHandler, useLocation } from "react-router-dom";
import * as S from "./style";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import InputComp from "../../../components/Board/InputComp";
import UploadForm from "../../../components/Board/UploadForm";
import KakaoKeywordSearch from "../../../components/Common/KakaoKeywordSearch/SearchBar";
import RegistBtn from "../../../components/Board/RegistBtn";
import { useNavigate } from "react-router-dom";
import CustomSelect from "../../../components/Common/CustomSelect";
import CustomCalendar from "../../../components/Board/CustomCalendar";

import boardApi from "../../../api/board";
import { recordApi } from "../../../api/record";
import { useSelector } from "react-redux";

const ALLOW_FILE_EXTENSION = "mp4,avi,wmv";
const FILE_SIZE_MAX_LIMIT = 10 * 1024 * 1024; // 10MB

const levelValues = [
  { key: "난이도 레벨", value: "" },
  { key: "LEVEL1", value: "1" },
  { key: "LEVEL2", value: "2" },
  { key: "LEVEL3", value: "3" },
  { key: "LEVEL4", value: "4" },
  { key: "LEVEL5", value: "5" },
  { key: "LEVEL6", value: "6" },
  { key: "LEVEL7", value: "7" },
];
const colorValues = [
  { key: "난이도 색상", value: "" },
  { key: "흰색", value: "흰색" },
  { key: "빨강", value: "빨강" },
  { key: "주황", value: "주황" },
  { key: "노랑", value: "노랑" },
  { key: "연두", value: "연두" },
  { key: "초록", value: "초록" },
  { key: "하늘", value: "하늘" },
  { key: "파랑", value: "파랑" },
  { key: "남색", value: "남색" },
  { key: "보라", value: "보라" },
  { key: "핑크", value: "핑크" },
  { key: "검정", value: "검정" },
  { key: "갈색", value: "갈색" },
  { key: "회색", value: "회색" },
];

const BoardForm = () => {
  const users = useSelector((state) => state.users);
  console.log(users);

  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [location, setLocation] = useState("");
  const [level, setLevel] = useState();
  const [color, setColor] = useState();
  const [selectDate, setSelectDate] = useState("");
  const [video, setVideo] = useState(null);

  // 라우터로부터 내려받은  props 데이터
  const propData = useLocation().state;

  const [propLevel, setPropLevel] = useState("");
  const [propColor, setPropColoer] = useState();

  useEffect(() => {
    if (!propData) return;

    recordApi
      .getOneRecordVideo(propData.id)
      .then((res) => {
        console.log("res: ", res);
        setLocation(res.data.result.gymName);
        setSelectDate(res.data.result.shootingDate);
        setVideo(res.data.result.s3URL);
        setLevel(res.data.result.level);
        setColor(res.data.result.color);
        setPropLevel(levelValues[res.data.result.level].key);
        console.log("왜이래이거: ", typeof res.data.result.level);
        setPropColoer(res.data.result.color);
      })
      .catch((err) => {
        console.log("err: ", err);
      });
  }, []);

  const submitHandler = () => {
    if (!title || !level || !color || !location || !selectDate || !video) {
      alert("모든 항목을 채워주세요.");
      return;
    }
    if (propData) {
      boardApi
        .postRegisterRecordVideo({ videoId: propData.id, title })
        .then((res) => {
          console.log("res: ", res);
          navigate("/board");
        })
        .catch((err) => {
          alert("영상등록에 실패했습니다.");
          console.log("err: ", err);
        });
      return;
    }

    const data = {
      title,
      level,
      color,
      createdDate: new Date(),
      gymName: location,
      shootingTime: selectDate,
    };

    const formData = new FormData();
    // Blob 객체 생성
    const blob = new Blob([JSON.stringify(data)], { type: "application/json" });

    console.log("video type ? ", typeof video);

    formData.append("localSuccessVideoUploadRequest", blob);
    formData.append("newVideo", video);
    boardApi
      .postRegisterLocalVideo(formData)
      .then((res) => {
        console.log("res", res);
        navigate("/board");
      })
      .catch((err) => {
        console.log("err: ", err);
        console.log("실패");
      });
  };

  const clickHandler = () => {
    if (!propData) navigate("/board");
    else navigate("/board/s3form");
  };

  const titleHandler = (e) => {
    setTitle(e.target.value);
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentWrap>
          <S.Content>
            <S.ArrowLeftBtnWrap>
              <ArrowLeftBtn clickHandler={clickHandler}></ArrowLeftBtn>
            </S.ArrowLeftBtnWrap>
            <S.ComponentWrap>
              <BoardSubTitle text="동영상" />
            </S.ComponentWrap>
            {propData ? (
              <S.UploadVideoWrap>
                <S.VideoContent src={video} muted controls />
              </S.UploadVideoWrap>
            ) : (
              <S.ComponentWrap>
                <UploadForm video={video} setVideo={setVideo}></UploadForm>
              </S.ComponentWrap>
            )}

            <S.ComponentWrap>
              <BoardSubTitle text="글등록" />
            </S.ComponentWrap>

            <S.ComponentWrap>
              <InputComp
                placeholder="제목을 입력해주세요"
                handleChange={setTitle}
                title={title}
              />
            </S.ComponentWrap>

            <S.ComponentWrap>
              <CustomCalendar
                selectDate={selectDate}
                setSelectDate={setSelectDate}
              />
            </S.ComponentWrap>

            <S.SelectButtonWrap>
              <S.selectBtnContent>
                <CustomSelect
                  defaultValue={propData ? propLevel : 0}
                  setter={setLevel}
                  optionValues={levelValues}
                />
                <CustomSelect
                  defaultValue={propData ? propColor : 0}
                  setter={setColor}
                  optionValues={colorValues}
                />
              </S.selectBtnContent>
            </S.SelectButtonWrap>

            <S.ComponentWrap>
              <KakaoKeywordSearch
                location={location}
                setLocation={setLocation}
              />
            </S.ComponentWrap>
            <S.ComponentWrap>
              <RegistBtn btnName="등록하기" clickHandler={submitHandler} />
            </S.ComponentWrap>
          </S.Content>
        </S.ContentWrap>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default BoardForm;

/**
 * 파일 확장자를 검사해주는 함수
 * @param param
 * @returns true : 가능 확장자, false : 불가능 확장자
 */

const fileExtensionValid = ({ video }) => {
  const extension = removeFileName(video);

  /**
   * 허용가능한 확장자가 있는지 확인하는 부분
   * 다양한 방법이 있지만 여기서는 indexof를 사용해서 확인.
   *
   * indexof의 경우
   * 허용가능한 확장자가 있을 경우
   * ALLOW_FILE_EXTENSION 상수의 해당 확장자 첫 index 위치값을 반환
   */

  if (!(ALLOW_FILE_EXTENSION.indexOf(extension) > -1) || extension === "") {
    // 해당 if문이 수행되는 조건은
    // 1. 허용하지 않은 확장자일 경우
    // 2. 확장자가 없는 경우
    return false;
  }
  return true;
};

/**
 * 해당 함수의 기능은 .을 제거한 순수 파일 확장자를 return해준다.
 * @param originalFileName 업로드할 파일명
 * @returns .을 제거한 순수 파일 확장자(mp4, 등)
 */
const removeFileName = ({ video }) => {
  // 마지막 .의 위치를 구한다.
  // 마지막 .의 위치 다음이 파일 확장자를 의미한다.
  const lastIndex = video.lastIndexof(".");

  // 파일 이름에서 .이 존재하지 않는 경우이다.
  // 이 경우 파일 확장자가 존재하지 않는 경우를 의미한다.
  if (lastIndex < 0) {
    return "";
  }

  // substring을 함수를 이용해 확장자만 잘라준다.
  // lastIndex의 값은 마지막 .의 위치이기 때문에 해당 위치 다음부터 끝까지 문자열을 잘라준다.
  // 문자열을 자른 후 소문자로 변경시켜 확장자 값을 반환 해준다.
  return video.substring(lastIndex + 1).toLowerCase();
};
