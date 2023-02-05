import React, { useState, useEffect } from "react";
import * as S from "./style";
import { Desktop, Mobile } from "../../layout/Template";
import { VscChromeClose } from "react-icons/vsc";

const UploadForm = () => {
  const [file, setFile] = useState();
  const [isSelected, setIsSelected] = useState(false);
  const handleFileChange = (e) => {
    if (e.target.files) {
      console.log(typeof e);
      setFile(e.target.files[0]);
      console.log(e);
      console.log(e);
      setIsSelected(true);
    }
  };

  const handleDeleteFile = () => {
    setFile();
    setIsSelected(false);
  };

  return (
    <S.Container>
      <S.UploadArea>
        {isSelected ? (
          <S.VideoWrap>
            <VscChromeClose
              className="cancelVideo"
              onClick={handleDeleteFile}
            />
            <video
              src={window.URL.createObjectURL(file)}
              muted
              controls
            ></video>
          </S.VideoWrap>
        ) : (
          <S.InputWrap>
            <label htmlFor="file">+ 업로드</label>
            <input type="file" id="file" onChange={handleFileChange} />
          </S.InputWrap>
        )}
      </S.UploadArea>
    </S.Container>
  );
};

export default React.memo(UploadForm);
