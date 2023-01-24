import React from "react";
import { ResponsivePie } from "@nivo/pie";

const data = [
  {
    id: "erlang",
    label: "erlang",
    value: 547,
    color: "hsl(160, 70%, 50%)",
  },
  {
    id: "ruby",
    label: "ruby",
    value: 353,
    color: "hsl(8, 70%, 50%)",
  },
  {
    id: "scala",
    label: "scala",
    value: 591,
    color: "hsl(103, 70%, 50%)",
  },
  {
    id: "haskell",
    label: "haskell",
    value: 448,
    color: "hsl(300, 70%, 50%)",
  },
  {
    id: "stylus",
    label: "stylus",
    value: 380,
    color: "hsl(126, 70%, 50%)",
  },
];

const StackedGraph = () => {
  return (
    <ResponsivePie
      width={300}
      height={300}
      data={data}
      margin={{ top: 0, right: 80, bottom: 0, left: 20 }}
      startAngle={0}
      innerRadius={0.5}
      padAngle={2}
      cornerRadius={2}
      activeOuterRadiusOffset={8}
      colors={{ scheme: "spectral" }}
      borderWidth={1}
      borderColor="#000000"
      enableArcLinkLabels={false}
      arcLinkLabelsSkipAngle={10}
      arcLinkLabelsTextColor="#333333"
      arcLinkLabelsThickness={2}
      arcLinkLabelsColor={{ from: "color" }}
      enableArcLabels={false}
      arcLabelsRadiusOffset={0}
      arcLabelsTextColor="#000000"
      fill={[
        {
          match: {
            id: "ruby",
          },
          id: "dots",
        },
        {
          match: {
            id: "c",
          },
          id: "dots",
        },
        {
          match: {
            id: "go",
          },
          id: "dots",
        },
        {
          match: {
            id: "python",
          },
          id: "dots",
        },
        {
          match: {
            id: "scala",
          },
          id: "lines",
        },
        {
          match: {
            id: "lisp",
          },
          id: "lines",
        },
        {
          match: {
            id: "elixir",
          },
          id: "lines",
        },
        {
          match: {
            id: "javascript",
          },
          id: "lines",
        },
      ]}
      legends={[
        {
          anchor: "right",
          direction: "column",
          justify: false,
          translateX: 164,
          translateY: -3,
          itemsSpacing: 5,
          itemWidth: 132,
          itemHeight: 24,
          itemTextColor: "#fff",
          itemDirection: "left-to-right",
          itemOpacity: 1,
          symbolSize: 18,
          symbolShape: "circle",
          effects: [
            {
              on: "hover",
              style: {
                itemTextColor: "#f2f2f2",
              },
            },
          ],
        },
      ]}
    />
  );
};

export default StackedGraph;
