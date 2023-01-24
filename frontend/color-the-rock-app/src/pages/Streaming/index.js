import React from "react";
import * as S from "./style";
const Streaming = () => {
  return (
    <S.Container>
      <S.Title>
        실시간 도전 <S.LiveTag>LIVE</S.LiveTag>
      </S.Title>
      <S.Description>
        도전 중인 등반을 보고 실시간으로 피드백해줘요!
      </S.Description>
    </S.Container>
  );
};
export default Streaming;
