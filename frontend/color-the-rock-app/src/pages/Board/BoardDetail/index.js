import React, {useState} from "react";
import { Desktop, Mobile } from "../../../components/layout/Template"
import * as S from "./style"
import ArrowLeftBtn from "../../../components/Common/ArrowLeftBtn"
import SubTitle from "../../../components/Common/SubTitle"
import CommentModal from "../../../components/Common/CommentModal";
import CommentBtn from "../../../components/Common/CommentBtn"
import { useNavigate } from "react-router";
import Title from "../../../components/Common/Title";
import Thumbnail from "../../../components/Common/Thumbnail";
import useInfiniteScroll from "../../../hooks/useInfiniteScroll"
import Header from "../../../components/layout/Header"
import BoardSubTitle from "../../../components/Board/BoardSubTitle"

const dummy = [
  {
    id: 1,
    title: "실시간 클라이밍 중",
    userNickname: "공싸피",
    gymName: "강남 더 클라이밍",
    imgUrl: "",
    color: "빨강",
  },
  {
    id: 2,
    title: "실시간 클라이밍 진행",
    userNickname: "김싸피",
    gymName: "역삼 더 클라이밍",
    imgUrl: "",
    color: "노랑",
  },
  {
    id: 3,
    title: "초보 클라이밍",
    userNickname: "송싸피",
    gymName: "홍대 더 클라이밍",
    imgUrl: "",
    color: "초록",
  },
  {
    id: 4,
    title: "클라이밍 마스터",
    userNickname: "최싸피",
    gymName: "인천 더 클라이밍",
    imgUrl: "",
    color: "파랑",
  },
  {
    id: 5,
    title: "실시간 클라이밍 중",
    userNickname: "허싸피",
    gymName: "신림 더 클라이밍",
    imgUrl: "",
    color: "보라",
  },
];


const BoardDetail = () => {
  
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [data, setData] = useState(dummy);
  
  const updateFuncOnScroll= () => {
   
    try {
      setData((prev) => [...prev, ...dummy]);
    } catch(error) {
    } finally {
      setIsFetching(false);
    }
  }

  const [isFetching, setIsFetching] = useInfiniteScroll(updateFuncOnScroll);

  const clickHandler = () => {
    navigate("/board");
  }

  const beforePage = () => {
    navigate("/board");
  }

  const handleModal = () => {
    console.log("change")
    setIsModalOpen(true);
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
      <S.ContentContainer>
        <S.ContentWrap>
          {/* 비디오 */}
          {/* <S.VideoWrap>
          </S.VideoWrap> */}
            <S.Video  controls>
              <source src="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4" type="video/mp4"/>
            </S.Video>
          
          {
            isModalOpen ? (
              <S.CommentModalWrap isModalOpen>
                <CommentModal setIsModalOpen={setIsModalOpen} />
              </S.CommentModalWrap>
            )
            : 
            (
              <S.FalseWrap>
              <S.ComponenentWrap>
                <div>
                  <BoardSubTitle text="살려주세요." />
                  <div>SSAFY는 사람을 찢어</div>
                </div>
                <div>2023-01-29</div>
              </S.ComponenentWrap>
              <S.CommentWrap onClick={handleModal}>
                <CommentBtn />
              </S.CommentWrap>
              
              <S.ComponenentWrap>
                <BoardSubTitle text="다른영상도 봐주세요" />
              </S.ComponenentWrap>
              
              <S.ThumbnailList>
                {data.map((item) => (
                  <Thumbnail
                    key={item.id}
                    id={item.id}
                    title={item.title}
                    userNickname={item.userNickname}
                    gymName={item.gymName}
                    imgUrl={item.imgUrl}
                    isLive={false}
                    color={item.color}
                  />
                ))}
              </S.ThumbnailList>
            </S.FalseWrap> 
            )
          }


        </S.ContentWrap>
      </S.ContentContainer>
    </S.Container>
    </div>
  )
};

export default BoardDetail;