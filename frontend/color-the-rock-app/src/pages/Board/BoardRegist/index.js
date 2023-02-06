import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
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

import BoardApi from "../../../api/board";
import { recordApi } from "../../../api/record";

const ALLOW_FILE_EXTENSION = "mp4,avi,wmv";
const FILE_SIZE_MAX_LIMIT = 10 * 1024 * 1024; // 10MB

const levelValues = [
  { key: "난이도 레벨", value: "" },
  { key: "LEVEL1", value: "level-1" },
  { key: "LEVEL2", value: "level-2" },
  { key: "LEVEL3", value: "level-3" },
  { key: "LEVEL4", value: "level-4" },
  { key: "LEVEL5", value: "level-5" },
  { key: "LEVEL6", value: "level-6" },
  { key: "LEVEL7", value: "level-7" },
];
const colorValues = [
  { key: "난이도 색상", value: "" },
  { key: "빨강", value: "red" },
  { key: "주황", value: "orange" },
  { key: "노랑", value: "yellow" },
  { key: "연두", value: "green" },
  { key: "하늘", value: "skyBlue" },
  { key: "남색", value: "indigo" },
  { key: "보리", value: "purple" },
  { key: "검정", value: "black" },
];

const BoardForm = () => {
  const navigate = useNavigate();
  const [title, setTitle] = useState();
  const [location, setLocation] = useState("");
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [selectDate, setSelectDate] = useState("");
  const [video, setVideo] = useState();
  console.log(typeof selectDate);
  const clickHandler = () => {
    navigate("/board");
  };

  // 라우터로부터 내려받은  props 데이터
  let { state, id } = useLocation();

  // state = true => s3upload
  // state = true;
  // 받아온 id에 대한 요청을 보낸다.
  useEffect(() => {
    recordApi
      .getRecordVideo(id)
      .then((res) => {
        setVideo(res);
      })
      .catch((err) => {
        console.log("err", err);
      });
  }, []);

  const submitHandler = () => {
    if (!title || !level || !color || !location || !selectDate || !video) {
      alert("모든 항목을 채워주세요.");
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
    const json = JSON.stringify(data);
    const blob = new Blob([json], { type: "application/json" });

    console.log("data: ", data);
    const formData = new FormData();
    formData.append("localSuccessVideoUploadRequest", blob);
    formData.append("newVideo", video);
    BoardApi.postRegisterLocalVideo(formData)
      .then((res) => {
        console.log("res", res);
        console.log("성공");
      })
      .catch((err) => {
        console.log("err: ", err);
        console.log("실패");
      });
    // navigate("/board");
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
            {state ? (
              <S.UploadVideoWrap>
                <video
                  src="https://dhw80hz67vj6n.cloudfront.net/20230203_091428077.mp4"
                  muted
                  controls
                ></video>
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
                handleChange={titleHandler}
                title={title}
                setTitle={setTitle}
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
                <CustomSelect setter={setLevel} optionValues={levelValues} />
                <CustomSelect setter={setColor} optionValues={colorValues} />
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
