import React, { useState, useEffect } from 'react'
import * as S from "./style"
import { Desktop, Mobile } from "../../layout/Template"

export default function UploadForm() {
  const [file, setFile] = useState();
  const [isSelected, setIsSelected] = useState(false);
  const handleFileChange = (e) => {
    if (e.target.files) {
      console.log(e);
      setFile(e.target.files[0]);
      setIsSelected(true);
      
    }
  };

  const handleDeleteFile = () => {
    setFile();
    setIsSelected(false);
  }

  return (
    <div>
      <Desktop></Desktop>
      <Mobile>
        <S.Container>
          <S.UploadArea>
            <S.Input type="file" onChange={handleFileChange}/>
            {isSelected ? (
              <div>
                <video ></video>
              </div>
            ):(<div></div>)}
          </S.UploadArea>
        </S.Container>
      </Mobile>
    </div>
  )
}
