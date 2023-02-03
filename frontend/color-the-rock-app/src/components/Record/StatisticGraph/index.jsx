import React, { useEffect, useState } from "react";
import { recordApi } from "../../../api/record";
import * as S from "./style";

const homeGymData = {
  totalCount: 11,
  gyms: [
    {
      id: 1,
      name: "더 클라이밍 강남",
      visitedCount: 3,
    },
    {
      id: 2,
      name: "더 클라이밍 홍대",
      visitedCount: 2,
    },
    {
      id: 3,
      name: "클라이밍짱짱",
      visitedCount: 5,
    },
    {
      id: 4,
      name: "클클클",
      visitedCount: 1,
    },
  ],
};

const StatisticGraph = ({ count = 3 }) => {
  const [result, setResult] = useState([]);
  const handleGetGymData = () => {
    console.log("handleGetGym");
    recordApi
      .getVisitedGymData()
      .then(({ data: { status, result } }) => {
        if (status === 200) {
          setResult(result);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    console.log("??????");
    handleGetGymData();
  }, []);

  return (
    <S.GraphWrapper>
      <S.GraphTitle>모든 도전</S.GraphTitle>
      <S.ChallengeBar>
        <S.Success count={count} />
        <S.BarLabel right="16px">{count}번의 성공</S.BarLabel>
        <S.BarLabel>10번의 도전</S.BarLabel>
      </S.ChallengeBar>

      <S.GraphTitle>방문한 홈짐</S.GraphTitle>
      <S.HomeGymGraph isResult={result.length === 0 && 0}>
        {result && result.length > 0
          ? result.map((gym, index) => (
              <S.VisitedState
                className="visited_state"
                key={gym.id}
                percent={(gym.visitedCount / homeGymData.totalCount) * 100}
                count={
                  0.01 *
                  (100 / homeGymData.gyms.length) *
                  (homeGymData.gyms.length - (index + 1))
                }
              />
            ))
          : null}
      </S.HomeGymGraph>
    </S.GraphWrapper>
  );
};
export default StatisticGraph;
