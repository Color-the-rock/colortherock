import React from "react";
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

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export const options = {
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
const data = {
  labels: labels,
  datasets: [
    {
      data: [12, 20, 6, 9, 2, 4, 2, 4, 10],
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

const StackedGraph = () => {
  return (
    <S.GraphWrapper>
      <Bar options={options} data={data} />
    </S.GraphWrapper>
  );
};
export default StackedGraph;
