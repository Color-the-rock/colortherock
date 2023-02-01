import React from "react";
import * as S from "./style";
import { useSearchParams } from "react-router-dom";
const Preview = () => {
  const [searchParams] = useSearchParams();
  const source = searchParams.get("source");

  return (
    <S.Container controls>
      <S.Video controls>
        <source src={source} type="video/mp4" />
      </S.Video>
    </S.Container>
  );
};
export default Preview;
