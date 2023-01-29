import React, { useState } from "react";
import { Desktop, Mobile } from "../../components/layout/Template";
import * as S from "./style";
import { FiArrowLeft } from "react-icons/fi";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import UploadForm from "../../components/Board/UploadForm";
import InputComp from "../../components/Board/InputComp";
import SelectButton from "../../components/Board/SelectButton";
import DatePickBtn from "../../components/Board/DatePickBtn";
// import KakaoMapBtn from '../../components/Board/KakaoMapBtn';
import RegistBtn from "../../components/Board/RegistBtn";
import { useNavigate } from "react-router-dom";
import SubTitle from "../../components/Common/SubTitle";
import CustomSelect from "../../components/Common/CustomSelect";
import { GuideWrapper } from "../../components/Common/InfoGuide/style";

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
export default function StreamingForm() {
  const navigate = useNavigate();

  const [startDate, setStartDate] = useState();
  const [title, setTitle] = useState("");
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [crag, setCrag] = useState("");
  const [location, setLocation] = useState("");

  const onClickHandler = () => {
    navigate("/streaming");
  };

  const submitHandler = () => {
    alert("방송시작");
    navigate("/streaming");
  };

  return (
    <div>
      <Desktop></Desktop>

      <Mobile>
        <S.Container>
          <S.CloseBtnContainer>
            <S.CloseBtn>
              <FiArrowLeft onClick={onClickHandler} />
            </S.CloseBtn>
          </S.CloseBtnContainer>

          <S.ContentWrap>
            <S.Content>
              <SubTitle text="미리보기" margin="16"></SubTitle>
              <UploadForm></UploadForm>

              <SubTitle text="방송 설정" margin="16">
                <S.InfoButton />
              </SubTitle>

              <InputComp placeholder="제목을 입력해주세요."></InputComp>
              <S.SelectWrap>
                <CustomSelect optionValues={levelValues} />
                <CustomSelect optionValues={colorValues} />
              </S.SelectWrap>
              <InputComp placeholder="암장을 입력해주세요."></InputComp>
              {/* <KakaoMapBtn location ={location} setLocation={setLocation}/> */}
              <RegistBtn
                btnName="방송 시작"
                clickHandler={submitHandler}
              ></RegistBtn>
            </S.Content>
          </S.ContentWrap>
        </S.Container>
      </Mobile>
    </div>
  );
}
