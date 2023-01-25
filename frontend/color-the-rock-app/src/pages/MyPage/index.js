import React, { useEffect, useState } from "react";
import * as S from "./style";
import Title from "../../components/Common/Title";
import { useInput } from "../../hooks/useInput";
import MyPost from "../../components/Mypage/MyPost";
import MyCommentList from "../../components/Mypage/MyCommentList";
const MyPage = () => {
  const [userNickName, onChangeUserNickName] = useInput("김싸피");
  const [radioValue, onChangeRadioButton] = useInput("post");
  const [isUpdate, setUpdate] = useState(false);

  return (
    <S.Container>
      <Title>마이페이지</Title>
      {isUpdate ? (
        <S.InfoWrapper>
          <S.NicknameInput
            type="text"
            defaultValue={userNickName}
            onChange={onChangeUserNickName}
          />
          <S.ButtonWrapper>
            <S.TextButton onClick={() => setUpdate((prev) => !prev)}>
              저장
            </S.TextButton>
            <S.TextButton onClick={() => setUpdate((prev) => !prev)}>
              취소
            </S.TextButton>
          </S.ButtonWrapper>
        </S.InfoWrapper>
      ) : (
        <S.InfoWrapper>
          <span>
            <S.Text>안녕하세요:)</S.Text>
            <S.NickName>{userNickName}</S.NickName>
            <S.Text>님</S.Text>
          </span>
          <S.TextButton onClick={() => setUpdate((prev) => !prev)}>
            수정
          </S.TextButton>
        </S.InfoWrapper>
      )}

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
      {/* 게시글 목록 OR 댓글 목록 */}
      {radioValue === "post" ? <MyPost></MyPost> : <MyCommentList />}
    </S.Container>
  );
};

export default MyPage;
