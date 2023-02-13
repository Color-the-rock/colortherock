import React, { useEffect, useState } from "react";
import { recordApi } from "../../../api/record";
import * as S from "./style";

const StatisticGraph = () => {
  const [gymData, setGymData] = useState([]);
  const [gymTotal, setGymTotal] = useState(0);
  const [totalRecords, setTotalRecords] = useState({
    videoCount: 0,
    successCount: 0,
  });

  // 방문한 홈장 데이터 조회
  const handleGetGymData = () => {
    recordApi
      .getVisitedGymData()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[getVisitedGymData] statusCode : 200 ", _result);
          setGymData(_result.data);
          setGymTotal(_result.totalCount);
        }
      })
      .catch((error) => console.log("error", error));
  };

  const handleGetTotalStatistics = () => {
    recordApi
      .getTotalStatistics()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[getTotalStatistics] statusCode : 200 ", _result);
          setTotalRecords(_result);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    handleGetGymData();
    handleGetTotalStatistics();
  }, []);

  return (
    <S.GraphWrapper>
      <S.GraphTitle>모든 도전</S.GraphTitle>
      <S.ChallengeBar>
        <S.Success
          successCount={totalRecords.successCount}
          videoCount={totalRecords.videoCount}
        />
        <S.BarLabel right="16px">
          {totalRecords.successCount !== 0 ? totalRecords.successCount : 0}번의
          성공
        </S.BarLabel>
        <S.BarLabel>
          {totalRecords.videoCount !== 0 ? totalRecords.videoCount : 0}번의 도전
        </S.BarLabel>
      </S.ChallengeBar>

      <S.GraphTitle>방문한 홈짐</S.GraphTitle>

      <S.HomeGymGraph length={gymTotal}>
        {gymData && gymData.length > 0
          ? gymData.map((gym, index) => (
              <S.VisitedState
                className="visited_state"
                key={index}
                percent={(gym.count / gymTotal) * 100}
                count={0.01 * (100 / gymTotal) * (gymTotal - (index + 1))}
                value={gym.gymName}
              >
                <S.GraphText>{gym.gymName}</S.GraphText>
              </S.VisitedState>
            ))
          : null}
      </S.HomeGymGraph>
    </S.GraphWrapper>
  );
};
export default StatisticGraph;
