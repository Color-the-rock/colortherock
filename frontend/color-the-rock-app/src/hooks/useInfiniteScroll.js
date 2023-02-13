/*

    Scroll Event를 이용한 useInfiniteScroll Custom Hook 구현
    
      - window.innerHeight : 현재 화면으로 보이는 윈도우의 높이
      - document.documentElement.scrollTop : 현재 화면이 어느 화면의 어느 좌표를 
            보고있는지를 알려주는 top 좌표 (얼마만큼 스크롤했는지로 생각하면 됨.)
      - document.documentElement.offsetHeight : 스크롤을 포함한 전체 페이지의 길이

      https://ha-young.github.io/2021/frontend/infinite-scroll/

*/

import { useState, useEffect } from "react";

export default function useInfiniteScroll(fatchCallback) {
  const [isFetching, setIsFetching] = useState(false);

  const handleScroll = () => {
    if (
      document.documentElement.scrollHeight -
        document.documentElement.scrollTop ===
      document.documentElement.clientHeight
    ) {
      setIsFetching(true);
    }
  };
  // scrollHeight

  useEffect(() => {
    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  useEffect(() => {
    if (!isFetching) return;

    fatchCallback();
  }, [isFetching]);

  return [isFetching, setIsFetching];
}
