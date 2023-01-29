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
import KakaoKeywordSearch from "../../components/Common/KakaoKeywordSearch/SearchBar";
import RegistBtn from "../../components/Board/RegistBtn";
import { useNavigate } from "react-router-dom";
import SubTitle from "../../components/Common/SubTitle";

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
    navigate("/streaming")
  }

  return (
    <div>
    </div>
  );
}

      // <Desktop></Desktop>

      // <Mobile>
      //   <S.Container>
      //     <S.CloseBtnContainer>
      //       <S.CloseBtn>
      //         <FiArrowLeft onClick={onClickHandler} />
      //       </S.CloseBtn>
      //     </S.CloseBtnContainer>

      //     <S.ContentWrap>
      //       <S.Content>
      //         <SubTitle text="미리보기" margin="16"></SubTitle>
      //         <UploadForm></UploadForm>
      //         <SubTitle text="방송 설정" margin="16"></SubTitle>
      //         <InputComp placeholder="제목을 입력해주세요."></InputComp>
      //         <S.SelectWrap>
      //           <SelectButton></SelectButton>
      //           <SelectButton></SelectButton>
      //         </S.SelectWrap>
      //         {/* <InputComp placeholder="암장을 입력해주세요."></InputComp> */}
      //         <KakaoKeywordSearch location ={location} setLocation={setLocation}/>
      //         <RegistBtn btnName="방송 시작" clickHandler={submitHandler}></RegistBtn>
      //       </S.Content>
      //     </S.ContentWrap>

      //     {/*  <S.TitleContainer>
      //         <S.Title>동영상</S.Title>
      //       </S.TitleContainer>
            
      //       <S.UploadContainer>
      //         <S.Upload>

      //         </S.Upload>
      //       </S.UploadContainer>

      //       <S.TitleContainer>
      //         <S.Title>글등록</S.Title>
      //       </S.TitleContainer>

      //       <S.ContentContainer>

      //       </S.ContentContainer>
      //       */}
      //   </S.Container>
      // </Mobile>