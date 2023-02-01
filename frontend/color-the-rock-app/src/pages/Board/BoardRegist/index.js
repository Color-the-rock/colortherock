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


import { defaultInstance } from "../../../api/utils"
import requests from "../../../api/board"

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

    defaultInstance.post(requests.RegistLocalVideo, {
      header: {

      },

      data: {

      }
    })
    .then(() => {
      navigate("/board");
    })
    .catch((err) => {
      console.log(err);
    })
    
  }
  
  return (
    <S.ContainerWrap>
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

            <DatePickBtn
              startDate={startDate}
              setStartDate={setStartDate}
            />
          <S.ComponenentWrap>
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
    </S.ContainerWrap>
  );
}