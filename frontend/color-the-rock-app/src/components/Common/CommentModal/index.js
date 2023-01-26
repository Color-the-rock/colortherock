import React, {useState} from 'react'
import * as S from "./style"
import CommentBtn from '../CommentBtn'

const CommentModal = ({setIsModalOpen}) => {

  const handleClick = () => {
    setIsModalOpen(false);
  }

  return (
    <S.Container>
      <S.CommentWrap>
        <S.OrnamentWrap>
          <S.Ornament />
        </S.OrnamentWrap>
        
        <S.CloseBtnWrap>
          <div>댓글</div>
          <div>X</div>
        </S.CloseBtnWrap>

        <S.CommentBtnWrap>
          <CommentBtn />
        </S.CommentBtnWrap>
      
        {/* <S.CommentList>
          
        </S.CommentList> */}
      </S.CommentWrap>
    </S.Container>
  )
}

export default CommentModal;

