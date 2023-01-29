import React, {useState} from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../../components/layout/Template"
import Header from '../../../components/layout/Header'
import ArrowLeftBtn from '../../../components/Common/ArrowLeftBtn'
import BoardSubTitle from "../../../components/Board/BoardSubTitle"
import InputComp from "../../../components/Board/InputComp"
import UploadForm from "../../../components/Board/UploadForm"
import KakaoKeywordSearch from "../../../components/Common/KakaoKeywordSearch/SearchBar"
import RegistBtn from "../../../components/Board/RegistBtn"
import { useNavigate } from "react-router-dom";
import SelectButton from "../../../components/Board/SelectButton";
import DatePickBtn from "../../../components/Board/DatePickBtn"
import 'react-datepicker/dist/react-datepicker.css';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';

import { ko } from "date-fns/esm/locale";
import BoardRadioBtn from '../../../components/Board/BoardRadioBtn/index'



export default function RecordForm() {
  const navigate = useNavigate();
  const [title, setTitle] = useState();
  const [location, setLocation] = useState("");
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  const [startDate, setStartDate] = useState(new Date());
  
  const clickHandler = () => {
    navigate("/record");
  }

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
          
          <S.SelectButtonWrap>
            <S.selectBtnContent>
              <SelectButton value={level} setValue={setLevel}/>
              <SelectButton value={color} setValue={setColor}/>
            </S.selectBtnContent>
          </S.SelectButtonWrap>

          <S.ComponenentWrap>
            {/* 라디오 버튼 만들어보자 */}
            <BoardRadioBtn></BoardRadioBtn>
          </S.ComponenentWrap>
          
          <S.ComponenentWrap>
            <KakaoKeywordSearch location={location} setLocation={setLocation} />
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
  )
}
