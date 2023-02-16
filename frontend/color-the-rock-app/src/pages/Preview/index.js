import React, { useEffect, useState } from "react";
import * as S from "./style";
import { useNavigate, useSearchParams } from "react-router-dom";
import { recordApi } from "../../api/record";
import ArrowLeftBtn from "../../components/Common/ArrowLeftBtn";
import Loading from "../../components/Common/Loading";
const Preview = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const videoId = searchParams.get("videoId");
  const [result, setResult] = useState([]);
  const [isLoading, setLoading] = useState(false);

  const handleOnClickDeleteVideo = () => {
    if (window.confirm("정말로 삭제하시겠습니까?")) {
      setLoading(true);
      recordApi
        .deleteRecordVideo(videoId)
        .then(() => {
          alert("정상적으로 삭제되었습니다:)");
          navigate("/record");
        })
        .catch((error) => console.log(error))
        .finally(() => setLoading(false));
    }
  };

  useEffect(() => {
    recordApi
      .getOneRecordVideo(videoId)
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          setResult(_result);
        }
      })
      .catch((data) => {
        if (data.response.data.status === 404) {
          alert("해당 영상을 찾을 수 없습니다.");
          navigate("/record");
        }
      });
  }, []);

  return isLoading ? (
    <Loading />
  ) : (
    <S.Container>
      <S.Wrapper>
        <ArrowLeftBtn clickHandler={() => navigate(`/record`)} />
        <S.Button onClick={handleOnClickDeleteVideo}>영상 삭제</S.Button>
      </S.Wrapper>
      <S.Video src={result.s3URL} type="video/mp4" controls></S.Video>
    </S.Container>
  );
};
export default Preview;
