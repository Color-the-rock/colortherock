import React, { useState } from "react";
import * as S from "./style";
import { Desktop, Mobile } from "../../../components/layout/Template";
import Header from "../../../components/layout/Header";
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
import BoardSubTitle from "../../../components/Board/BoardSubTitle";
import InputComp from "../../../components/Board/InputComp";
import UploadForm from "../../../components/Board/UploadForm";
import KakaoKeywordSearch from "../../../components/Common/KakaoKeywordSearch/SearchBar";
import RegistBtn from "../../../components/Board/RegistBtn";
import { useNavigate } from "react-router-dom";
import CustomSelect from "../../../components/Common/CustomSelect";
import CustomCalendar from "../../../components/Board/CustomCalendar";

import "react-datepicker/dist/react-datepicker.css";
import "react-datepicker/dist/react-datepicker-cssmodules.css";

import { ko } from "date-fns/esm/locale";
import BoardRadioBtn from "../../../components/Board/BoardRadioBtn/index";

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

const RecordForm = () => {
  const navigate = useNavigate();
  const [title, setTitle] = useState();
  const [location, setLocation] = useState("");
  const [level, setLevel] = useState("");
  const [color, setColor] = useState("");
  // const [startDate, setStartDate] = useState(new Date());
  const [selectDate, setSelectDate] = useState("");
  const [isSuccess, setIsSuccess] = useState(true);

  console.log(typeof selectDate);
  const clickHandler = () => {
    navigate("/board");
  };

  const submitHandler = () => {
    navigate("/board");
  };

  const check = () => {
    const val = document.getElementById("dd");
    console.log(val.value);
  };
  const handler = (e) => {
    // console.log(e.target.value);
    setSelectDate(e.target.value);
    // setStartDate(e.target.value);
    console.log(selectDate);
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

            <S.ComponentWrap>
              <UploadForm></UploadForm>
            </S.ComponentWrap>

            <S.ComponentWrap>
              <BoardSubTitle text="글등록" />
            </S.ComponentWrap>

            <S.ComponentWrap>
              <InputComp
                placeholder="제목을 입력해주세요."
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
                <CustomSelect optionValues={levelValues} />
                <CustomSelect optionValues={colorValues} />
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

export default RecordForm;

// import React, { useState } from "react";
// import { Mobile, Desktop } from "../../../components/layout/Template";
// import * as S from "./style";
// import { useNavigate } from "react-router-dom";

// import Header from "../../../components/layout/Header";
// import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn";
// import BoardSubTitle from "../../../components/Board/BoardSubTitle";
// import UploadForm from "../../../components/Board/UploadForm";
// import InputComp from "../../../components/Board/InputComp";
// import SelectButton from "../../../components/Board/SelectButton";
// import SearchBar from "../../../components/Common/KakaoKeywordSearch/SearchBar";
// import DatePickBtn from "../../../components/Board/DatePickBtn";
// import RegistBtn from "../../../components/Board/RegistBtn";

// import { defaultInstance } from "../../../api/utils";
// import requests from "../../../api/board";

// export default function BoardRegist() {
//   const navigate = useNavigate();
//   const [startDate, setStartDate] = useState();
//   const [title, setTitle] = useState("");
//   const [level, setLevel] = useState("");
//   const [color, setColor] = useState("");
//   const [location, setLocation] = useState("");

//   const clickHandler = () => {
//     navigate("/board");
//   };

//   const submitHandler = () => {
//     defaultInstance
//       .post(requests.RegistLocalVideo, {
//         header: {},

//         data: {},
//       })
//       .then(() => {
//         navigate("/board");
//       })
//       .catch((err) => {
//         console.log(err);
//       });
//   };

//   return (
//     <S.ContainerWrap>
//       <Desktop>
//         <S.HeaderWrap>
//           <Header></Header>
//         </S.HeaderWrap>
//       </Desktop>
//       <Mobile>
//         <S.ArrowLeftBtnWrap>
//           <ArrowLeftBtn clickHandler={clickHandler}></ArrowLeftBtn>
//         </S.ArrowLeftBtnWrap>
//       </Mobile>

//       <S.Container>
//         <S.ContentWrap>
//           <S.Content>
//             <S.ComponenentWrap>
//               <BoardSubTitle text="동영상" />
//             </S.ComponenentWrap>

//             <S.ComponenentWrap>
//               <UploadForm></UploadForm>
//             </S.ComponenentWrap>

//             <S.ComponenentWrap>
//               <BoardSubTitle text="글등록" />
//             </S.ComponenentWrap>

//             <S.ComponenentWrap>
//               <InputComp
//                 placeholder="제목을 입력해주세요."
//                 title={title}
//                 setTitle={setTitle}
//               />
//             </S.ComponenentWrap>

//             <S.SelectButtonWrap>
//               <S.selectBtnContent>
//                 <SelectButton value={level} setValue={setLevel} />
//                 <SelectButton value={color} setValue={setColor} />
//               </S.selectBtnContent>
//             </S.SelectButtonWrap>

//             <S.ComponenentWrap>
//               <SearchBar location={location} setLocation={setLocation} />
//             </S.ComponenentWrap>

//             <DatePickBtn startDate={startDate} setStartDate={setStartDate} />
//             <S.ComponenentWrap></S.ComponenentWrap>

//             <S.ComponenentWrap>
//               <RegistBtn btnName="등록하기" clickHandler={submitHandler} />
//             </S.ComponenentWrap>

//             {/* <DatePicker
//               selected={startDate}
//               onChange={(date) => setStartDate(date)}
//             /> */}
//           </S.Content>
//         </S.ContentWrap>
//       </S.Container>
//     </S.ContainerWrap>
//   );
// }
