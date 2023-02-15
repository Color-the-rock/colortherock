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
import Loading from "../../../components/Common/Loading";
import boardApi from "../../../api/board";
import { recordApi } from "../../../api/record";

const levelValues = [
  { key: "난이도 레벨", value: "" },
  { key: "LEVEL1", value: "1" },
  { key: "LEVEL2", value: "2" },
  { key: "LEVEL3", value: "3" },
  { key: "LEVEL4", value: "4" },
  { key: "LEVEL5", value: "5" },
  { key: "LEVEL6", value: "6" },
  { key: "LEVEL7", value: "7" },
  { key: "LEVEL8", value: "8" },
  { key: "LEVEL9", value: "9" },
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
  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [location, setLocation] = useState("");
  const [level, setLevel] = useState();
  const [color, setColor] = useState();
  const [selectDate, setSelectDate] = useState("");
  const [video, setVideo] = useState(null);
  const [isLoading, setLoading] = useState(false);

  // 라우터로부터 내려받은  props 데이터
  const propData = useLocation().state;

  const [propLevel, setPropLevel] = useState("");
  const [propColor, setPropColor] = useState();

  useEffect(() => {
    if (!propData) return;

    recordApi
      .getOneRecordVideo(propData.id)
      .then((res) => {
        setLocation(res.data.result.gymName);
        setSelectDate(res.data.result.shootingDate);
        setVideo(res.data.result.s3URL);
        setLevel(res.data.result.level);
        setColor(res.data.result.color);
        setPropLevel(levelValues[res.data.result.level].key);
        setPropColor(res.data.result.color);
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

    setLoading(true);

    if (propData) {
      boardApi
        .postRegisterRecordVideo({ videoId: propData.id, title })
        .then((res) => {
          navigate("/board");
        })
        .catch((err) => {
          alert("영상등록에 실패했습니다.");
          console.log("err: ", err);
        })
        .finally(() => setLoading(false));
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

    formData.append("localSuccessVideoUploadRequest", blob);
    formData.append("newVideo", video);

    boardApi
      .postRegisterLocalVideo(formData)
      .then(() => {
        navigate("/board");
      })
      .catch((err) => {
        console.log("err: ", err);
        console.log("실패");
      })
      .finally(() => setLoading(false));
  };

  const clickHandler = () => {
    if (!propData) navigate("/board");
    else navigate("/board/s3form");
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
        {isLoading && <Loading />}
      </S.Container>
    </S.ContainerWrap>
  );
};

export default BoardForm;
