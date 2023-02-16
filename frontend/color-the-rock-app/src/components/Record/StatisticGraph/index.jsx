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
          console.log("visited!!", _result);
          let top3Data = [];

          for (let i = 0; i < _result.length; i++) {
            console.log("data?>>> ", i, " ", _result.data[i]);
            if (i > 2) return;
            top3Data.push(_result.data[i]);
          }

          console.log("top3Data?? ", top3Data);

          setGymData(top3Data);
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
          setTotalRecords(_result);
        }
      })
      .catch((error) => console.log("error", error));
  };

  const handleShowTooltip = (e) => {
    console.log("[handleShowTooltip] : e", e);
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

      <S.GraphTitle>방문 암장 TOP3</S.GraphTitle>

      <S.HomeGymGraph length={gymTotal}>
        {gymData && gymData.length > 0
          ? gymData.map((gym, index) => (
              <S.VisitedState
                className="visited_state"
                key={index}
                percent={(gym.count / 3) * 100}
                count={0.01 * (100 / 3) * (3 - (index + 1))}
                value={gym.gymName}
                onMouseEnter={handleShowTooltip(gym.gymName)}
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
