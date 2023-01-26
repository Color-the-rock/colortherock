import React, {useState} from 'react'
import * as S from "./style"
import CommentBtn from '../CommentBtn'
import { VscChromeClose } from "react-icons/vsc";
const CommentModal = ({setIsModalOpen}) => {

  const closeModalHandler = () => {
    setIsModalOpen(false);
  }

  return (
    <S.Container>
      <S.CommentWrap>
        {/* 장식용 바 */}
        <S.OrnamentWrap>
          <S.Ornament />
        </S.OrnamentWrap>
        
        <S.CloseBtnWrap>
          <div>댓글</div>
          <VscChromeClose className='' onClick={closeModalHandler}/>
        </S.CloseBtnWrap>

        <S.CommentBtnWrap>
          <CommentBtn isReadOnly={false}/>
        </S.CommentBtnWrap>
      
        {/* <S.CommentList>
          
        </S.CommentList> */}
      </S.CommentWrap>
    </S.Container>
  )
}

export default React.memo(CommentModal);

