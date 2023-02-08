import React, { useEffect, useState } from "react";
import { recordApi } from "../../../api/record";
import * as S from "./style";

const StatisticGraph = ({ count = 3 }) => {
  const [result, setResult] = useState([]);
  const [totalCount, setTotalCount] = useState(0);

  const getTotalCount = (_result) => {
    if (_result.length === 0) return;
    let tmp = 0;
    for (let item of _result) {
      tmp += Number(item.count);
    }
    setTotalCount(tmp);
  };

  const handleGetGymData = () => {
    recordApi
      .getVisitedGymData()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[getVisitedGymData] statusCode : 200 ", _result);
          setResult(_result);
          getTotalCount(_result);
          console.log("_result ? ", _result);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
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
      <S.HomeGymGraph length={result.length === 0 ? 0 : result.length}>
        {result && result.length > 0
          ? result.map((gym, index) => (
              <S.VisitedState
                className="visited_state"
                key={index}
                percent={(gym.count / totalCount) * 100}
                count={
                  0.01 * (100 / result.length) * (result.length - (index + 1))
                }
              />
            ))
          : null}
      </S.HomeGymGraph>
    </S.GraphWrapper>
  );
};
export default StatisticGraph;
