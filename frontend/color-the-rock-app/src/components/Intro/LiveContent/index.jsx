import React from "react";
import BasicButton from "../../Common/BasicButton";
import * as S from "./style";

// props : imgURL
const Content = () => {
  return (
    <S.Container>
      <S.Text>실시간으로 소통하며</S.Text>
      <S.Text>함께 볼더링 문제를 풀어봐요!</S.Text>
      <BasicButton text="실시간 도전 보러가기" link="/streaming" />
    </S.Container>
  );
};

export default Content;
