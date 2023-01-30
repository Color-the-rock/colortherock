import React, { useState } from "react";
import { Mobile, Desktop } from "../../../components/layout/Template";
import * as S from "./style";
import { useNavigate } from "react-router-dom";

import Header from "../../../components/layout/Header"
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn"
import BoardSubTitle from "../../../components/Board/BoardSubTitle"
import UploadForm from "../../../components/Board/UploadForm"
import InputComp from "../../../components/Board/InputComp"
import SelectButton from "../../../components/Board/SelectButton"
import SearchBar from "../../../components/Common/KakaoKeywordSearch/SearchBar"
import DatePickBtn from "../../../components/Board/DatePickBtn"
import RegistBtn from "../../../components/Board/RegistBtn"

export default function BoardRegist() {
  const navigate = useNavigate();
  const [startDate, setStartDate] = useState();
  const [title, setTitle] = useState("");
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [location, setLocation] = useState("");

  const clickHandler = () => {
    navigate("/board");
  };

  const submitHandler = () => {
    navigate("/board");
  }
  
  return (
    <div>
      <Desktop>
        <S.HeaderWrap>
          <Header></Header>
        </S.HeaderWrap>
      </Desktop>
      <Mobile>
        <S.ArrowLeftBtnWrap>
          <ArrowLeftBtn clickHandler={clickHandler}></ArrowLeftBtn>
        </S.ArrowLeftBtnWrap>
      </Mobile>
    
    <S.Container>
      <S.ContentWrap>
        <S.Content>
          <S.ComponenentWrap>
            <BoardSubTitle text="동영상" />
          </S.ComponenentWrap>
          
          <S.ComponenentWrap>
            <UploadForm></UploadForm>
          </S.ComponenentWrap>
          
          <S.ComponenentWrap>
            <BoardSubTitle text="글등록" />
          </S.ComponenentWrap>
          
          <S.ComponenentWrap>
            <InputComp placeholder="제목을 입력해주세요." title={title} setTitle={setTitle} />
          </S.ComponenentWrap>
          
          <S.SelectButtonWrap>
            <S.selectBtnContent>
              <SelectButton value={level} setValue={setLevel}/>
              <SelectButton value={color} setValue={setColor}/>
            </S.selectBtnContent>
          </S.SelectButtonWrap>
          
          <S.ComponenentWrap>
            <SearchBar location={location} setLocation={setLocation} />
          </S.ComponenentWrap>

          <S.ComponenentWrap>
            <DatePickBtn
              startDate={startDate}
              setStartDate={setStartDate}
            />
          </S.ComponenentWrap>

          <S.ComponenentWrap>
          <RegistBtn btnName="등록하기" clickHandler={submitHandler} />
          </S.ComponenentWrap>

          

            {/* <DatePicker
              selected={startDate}
              onChange={(date) => setStartDate(date)}
            /> */}


        </S.Content>
      </S.ContentWrap>
    </S.Container>
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
      //         <SubTitle text="동영상" margin="16"></SubTitle>
      //         <UploadForm></UploadForm>
      //         <SubTitle text="글등록" margin="16"></SubTitle>
      //         <InputComp placeholder="제목을 입력해주세요." setTitle={setTitle}></InputComp>
      //         <S.SelectWrap>
      //           <SelectButton />
      //           <SelectButton />
      //         </S.SelectWrap>
      //         <KakaoKeywordSearch location={location} setLocation={setLocation} />
      //         <DatePickBtn
      //           startDate={startDate}
      //           setStartDate={setStartDate}
      //         ></DatePickBtn>
      //         <RegistBtn btnName="등록하기" clickHandler={submitHandler}></RegistBtn>
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