import React, { useEffect, useState } from "react";
import { recordApi } from "../../../api/record";
import * as S from "./style";

const StatisticGraph = () => {
  const [gymData, setGymData] = useState([]);
  const [totalRecords, setTotalRecords] = useState({
    videoCount: 0,
    successCount: 0,
  });
  // const [totalCount, setTotalCount] = useState(0);

  // const getTotalCount = (_result) => {
  //   if (_result.length === 0) return;
  //   let tmp = 0;
  //   for (let item of _result) {
  //     tmp += Number(item.count);
  //   }
  //   setTotalCount(tmp);
  // };

  const handleGetGymData = () => {
    recordApi
      .getVisitedGymData()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("[getVisitedGymData] statusCode : 200 ", _result);
          setGymData(_result);
          //getTotalCount(_result);
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
      <S.HomeGymGraph length={totalRecords.visitCount}>
        {gymData && gymData.length > 0
          ? gymData.map((gym, index) => (
              <S.VisitedState
                className="visited_state"
                key={index}
                percent={(gym.count / totalRecords.visitCount) * 100}
                count={
                  0.01 * (100 / gymData.length) * (gymData.length - (index + 1))
                }
              />
            ))
          : null}
      </S.HomeGymGraph>
    </S.GraphWrapper>
  );
};
export default StatisticGraph;
