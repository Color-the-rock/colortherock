import { Mobile, Desktop } from "../../../components/layout/Template"
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { useInfiniteScroll } from "../../../hooks/useInfiniteScroll"


import Video from "../../../components/Board/BoardList"

import * as S from "./style";
import axios from "axios";

// import {defaultInstance, authApi} from "../../api/utils/index";
const Board = () => {

  const [isFetching, setIsFetching] = useInfiniteScroll(updataFunctionOnScroll);

  const [videoData, setVideoData] = useState([]);
  const [searchValue, setSearchValue] = useState("");
  
  
  const navigate = useNavigate();

  
  useEffect(() => {
    fetchData();
  }, [])
  
  // 영상데이터 불러오기
  const fetchData = async () => {
    console.log("영상 불러오기");
    const res = await axios.get("https://api.themoviedb.org/3//movie/top_rated?api_key=c4c61062f0586b0c14f0df48b5eda1f1");
    console.log("data",  res);
    setVideoData(res.data.results);
    // const possible = await authApi.get(" ");
    // if(possible) {
    //   const request = await defaultInstance.get(" ");
    //   setVideoData(request.result);
    // } else {

    // }
  }
  const updataFunctionOnScroll = async () => {
    try {
      const res = await fetchFunction();
      setVideoData((prev) => [res, ...prev]);
    } catch(error) {
      
    } finally {
      setIsFetching(false);
    }
  }
  }

  const handleChange = (e) => {
    setSearchValue(e.target.value);
  }

  return (
  <div>
    <Desktop>
      <S.Container>

      </S.Container>
    </Desktop>
    <Mobile>
      <S.Container>
        <h2>완등 영상 보기</h2>
        <hr/>
        <div>보고 싶은 레벨과 색상을 입력하세요.</div>
        <input
          value={searchValue}
          onChange={handleChange}
          type="text"
          placeholder="암장 입력."
        >
        </input>
        {
          videoData.length > 0 &&
          <S.VideoWrap>
            {
              videoData.map((data, index) => (
                <Video 
                  key={index}
                  data={data}
                />
                
              ))
            }
          </S.VideoWrap>
        }
      </S.Container>
    </Mobile>
  </div>
  );
};
export default Board;


// 무한스크롤 시 마지막 video id를 넘겨준다. 