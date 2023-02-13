import React from "react";
import * as S from "./style";
import LoadingImg from "../../../assets/img/common/img-loading.png";
const Loading = () => (
  <S.LoadingWrapper>
    <S.Icon src={LoadingImg} width="64spx" />
    <S.Text>Loading...</S.Text>
  </S.LoadingWrapper>
);

export default Loading;
