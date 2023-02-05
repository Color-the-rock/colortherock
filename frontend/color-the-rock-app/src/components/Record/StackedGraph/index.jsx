import React, { useEffect, useState } from "react";
import * as S from "./style";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";
import { recordApi } from "../../../api/record";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

// 그래프 설정 관련 변수
const options = {
  plugins: {
    title: {
      display: false,
    },
    legend: {
      display: false,
    },
  },
  responsive: true,
  scales: {
    x: {
      stacked: false,
    },
    y: {
      stacked: false,
    },
  },
};

const labels = [
  "white",
  "red",
  "orange",
  "yellow",
  "green",
  "skyblue",
  "indigo",
  "purple",
  "brown",
];

const StackedGraph = () => {
  const [result, setResult] = useState([]);

  // 선택된 버튼에 따른 그래프 데이터 필터 처리
  const handleFilterData = (_result = result, type = "success") => {
    return type === "success"
      ? _result && _result.length > 0 && _result.map((item) => item.success)
      : _result && _result.length > 0 && _result.map((item) => item.total);
  };

  const data = {
    labels: labels,
    datasets: [
      {
        data: handleFilterData(),
        backgroundColor: [
          "rgba(255, 255, 255, 0.6)",
          "rgba(255, 78, 54, 0.6)",
          "rgba(255, 166, 46, 0.6)",
          "rgba(255, 207, 27, 0.6)",
          "rgba(192, 250, 135, 0.6)",
          "rgba(110, 226, 245, 0.6)",
          "rgba(60, 93, 211, 0.6)",
          "rgba(133, 51, 255, 0.6)",
          "rgba(105, 95, 84, 0.6)",
        ],
        borderColor: [
          "rgb(255, 255, 255)",
          "rgb(255, 78, 54)",
          "rgb(255, 166, 46)",
          "rgb(255, 207, 27)",
          "rgb(192, 250, 135)",
          "rgb(110, 226, 245)",
          "rgb(60, 93, 211)",
          "rgb(133, 51, 255)",
          "rgba(105, 95, 84, 1)",
        ],
        borderWidth: 1,
        borderRadius: 5,
      },
    ],
  };

  // API 연결
  const handleGetColorStatistics = () => {
    recordApi
      .getColorStatistics()
      .then(({ data: { status, result: _result } }) => {
        if (status === 200) {
          console.log("statusCode : 200 ", _result);
          setResult(_result);
        }
      });
  };

  useEffect(() => {
    handleGetColorStatistics();
  }, []);

  return (
    <S.GraphWrapper>
      <Bar options={options} data={data} />
    </S.GraphWrapper>
  );
};
export default StackedGraph;
