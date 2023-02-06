import React, { useState } from "react";
import { Desktop, Mobile } from "../../layout/Template";
import * as S from "./style";

const VideoClip = () => {
  const [isPublisher, setIsPublisher] = useState(true);

  const onClickHandler = () => {};

  return (
    <>
      <Desktop></Desktop>
      <Mobile>
        <S.ContainerWrap>
          <S.Container>
            <S.ContentBox>
              {/* Publisher만 보이는 버튼 =>  */}
              {isPublisher && <button onClick={onClickHandler}></button>}
              만드는 중입니다.
            </S.ContentBox>
          </S.Container>
        </S.ContainerWrap>
      </Mobile>
    </>
  );
};

export default VideoClip;
