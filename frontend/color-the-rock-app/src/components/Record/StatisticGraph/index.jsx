import React from "react";
import { ResponsiveBar } from "@nivo/bar";

const data = [
  {
    country: "AE",
    "hot dog": 14,
    "hot dogColor": "hsl(248, 70%, 50%)",
    burger: 49,
    burgerColor: "hsl(43, 70%, 50%)",
    sandwich: 158,
    sandwichColor: "hsl(73, 70%, 50%)",
    kebab: 43,
    kebabColor: "hsl(121, 70%, 50%)",
    fries: 1,
    friesColor: "hsl(32, 70%, 50%)",
    donut: 59,
    donutColor: "hsl(2, 70%, 50%)",
  },
];
const StatisticGraph = () => {
  return (
    <ResponsiveBar
      data={data}
      keys={["hot dog", "burger", "sandwich", "kebab", "fries", "donut"]}
      indexBy="country"
      margin={{ top: 50, right: 20, bottom: 50, left: 20 }}
      padding={0.8}
      layout="horizontal"
      valueScale={{ type: "linear" }}
      indexScale={{ type: "band", round: true }}
      colors={{ scheme: "red_purple" }}
      fill={[
        {
          match: {
            id: "fries",
          },
          id: "dots",
        },
        {
          match: {
            id: "sandwich",
          },
          id: "lines",
        },
      ]}
      borderColor="black"
      axisTop={null}
      axisRight={null}
      axisBottom={{
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "country",
        legendPosition: "middle",
        legendOffset: 32,
      }}
      axisLeft={{
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "food",
        legendPosition: "middle",
        legendOffset: -40,
      }}
      enableLabel={false}
      labelSkipWidth={12}
      labelSkipHeight={12}
      labelTextColor={{
        from: "color",
        modifiers: [["darker", 1.6]],
      }}
      legends={[
        {
          dataFrom: "keys",
          anchor: "top",
          direction: "row",
          justify: false,
          translateX: 20,
          translateY: 0,
          itemsSpacing: 2,
          itemWidth: 100,
          itemHeight: 20,
          itemDirection: "left-to-right",
          itemOpacity: 0.85,
          symbolSize: 20,
          effects: [
            {
              on: "hover",
              style: {
                itemOpacity: 1,
              },
            },
          ],
        },
      ]}
      role="application"
      ariaLabel="Nivo bar chart demo"
      barAriaLabel={function (e) {
        return e.id + ": " + e.formattedValue + " in country: " + e.indexValue;
      }}
    />
  );
};

export default StatisticGraph;
