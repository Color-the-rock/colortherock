import React, { useState, useEffect } from 'react'
const { kakao } = window;

const KakaoSearchList = ({searchPlace}) => {

  const [searchData, setSearchData] = useState([]);
  console.log("kakao: ", kakao);
  useEffect(() => {
    const ps = new kakao.maps.services.Places();
    console.log("왜 두번씩 만들어지는거야?");
    // console.log(ps);
    ps.keywordSearch({searchPlace}, placesSearchCB);

    function placesSearchCB(data, status, pagination) {
      console.log("data", data);
      setSearchData(data);
    }

    console.log("ps", ps);
  }, []);

  return (
    <div>

    </div>
  )

}


export default KakaoSearchList;