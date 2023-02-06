import React from "react";
import * as S from "./style";
import Title from "../../components/Common/Title";
import { useInput } from "../../hooks/useInput";
import MyPost from "../../components/Mypage/MyPost";
import MyCommentList from "../../components/Mypage/MyCommentList";
import { useSelector } from "react-redux";

const MyPage = () => {
  const userNickName = useSelector((state) => state.user.nickName);
  const [radioValue, onChangeRadioButton] = useInput("post");

  return (
    <S.Container>
      <Title>마이페이지</Title>

      <S.InfoWrapper>
        <span>
          <S.NickName>{userNickName ? userNickName : "사용자"}</S.NickName>
          <S.Text>님, 안녕하세요:)</S.Text>
        </span>
      </S.InfoWrapper>

      <S.RadioGroup>
        <S.RadioLabel checked={radioValue === "post"}>
          <S.RadioButton
            type="radio"
            name="type"
            value="post"
            checked={radioValue === "post"}
            onChange={onChangeRadioButton}
          />
          내 게시글
        </S.RadioLabel>
        <S.RadioLabel checked={radioValue === "comment"}>
          <S.RadioButton
            type="radio"
            name="type"
            value="comment"
            checked={radioValue === "comment"}
            onChange={onChangeRadioButton}
          />
          내 댓글
        </S.RadioLabel>
      </S.RadioGroup>
      {radioValue === "post" ? <MyPost /> : <MyCommentList />}
    </S.Container>
  );
};

export default MyPage;
