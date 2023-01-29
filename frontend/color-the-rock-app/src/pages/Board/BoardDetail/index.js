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
      </Desktop>
      
      <Mobile>
        <S.Container>
          <ArrowLeftBtn clickHandler={beforePage}></ArrowLeftBtn>
          {/* 타이틀 + 운동한 날 */}
          <S.TitleWrap>
            <div>실시간 스트리밍 중</div>
          </S.TitleWrap>
        </S.Container>
        
          {/* 비디오 */}
          <S.VideoWrap>
            <S.Video controls>
              <source src="" type="video/mp4"></source>
            </S.Video>
          </S.VideoWrap>
        
        <S.Container>
          {
            isModalOpen ? (
              <S.CommentModalWrap isModalOpen>
                <CommentModal setIsModalOpen={setIsModalOpen} />
              </S.CommentModalWrap>
            ) : (
            <div>
              {/* 댓글 버튼 */}
              <S.CommentWrap onClick={handleModal}>
                <CommentBtn />
              </S.CommentWrap>
              <S.TitleWrap>
                <div>다른 완등 영상 보기</div>
              </S.TitleWrap>
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
            </div>
            )
          }
        </S.Container>
      </Mobile>  
    </div>
  )
};

export default BoardDetail;
