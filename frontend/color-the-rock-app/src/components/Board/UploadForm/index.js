import React, { useState } from "react";
import * as S from "./style";
import { FiX } from "react-icons/fi";

const ALLOW_FILE_EXTENSION = "mp4,avi,wmv,webm,mov,flv,mkv";
const FILE_SIZE_MAX_LIMIT = 100 * 1024 * 1024; // 100MB

const UploadForm = ({ video, setVideo, isLoading }) => {
  const [isSelected, setIsSelected] = useState(false);

  const handleFileChange = (e) => {
    if (e.target.files) {
      const files = e.target.files[0];
      // 파일 확장자 체크
      if (!fileExtensionValid(files.name)) {
        alert(
          `업로드 가능한 확장자가 아닙니다. [가능한 확장자 : ${ALLOW_FILE_EXTENSION}]`
        );
        return;
      }

      // 파일 용량 체크
      if (files.size > FILE_SIZE_MAX_LIMIT) {
        alert("업로드 가능한 최대 용량은 10MB입니다.");
        return;
      }
      console.log("video: ", e.target.files[0]);
      setVideo(e.target.files[0]);
      setIsSelected(true);
    }
  };

  const handleDeleteFile = () => {
    setVideo();
    setIsSelected(false);
  };

  return (
    <S.Container>
      <S.UploadArea>
        {isSelected ? (
          <S.VideoWrap>
            {isLoading ? null : (
              <FiX
                isLoading={isLoading}
                className="cancelVideo"
                onClick={handleDeleteFile}
              />
            )}
            <video
              src={window.URL.createObjectURL(video)}
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

/**
 * 파일 확장자를 검사해주는 함수
 * @param param
 * @returns true : 가능 확장자, false : 불가능 확장자
 */

const fileExtensionValid = (files) => {
  const extension = removeFileName(files);

  /**
   * 허용가능한 확장자가 있는지 확인하는 부분
   * 다양한 방법이 있지만 여기서는 indexof를 사용해서 확인.
   *
   * indexof의 경우
   * 허용가능한 확장자가 있을 경우
   * ALLOW_FILE_EXTENSION 상수의 해당 확장자 첫 index 위치값을 반환
   */

  if (!(ALLOW_FILE_EXTENSION.indexOf(extension) > -1) || extension === "") {
    // 해당 if문이 수행되는 조건은
    // 1. 허용하지 않은 확장자일 경우
    // 2. 확장자가 없는 경우
    return false;
  }

  return true;
};

/**
 * 해당 함수의 기능은 .을 제거한 순수 파일 확장자를 return해준다.
 * @param originalFileName 업로드할 파일명
 * @returns .을 제거한 순수 파일 확장자(mp4, 등)
 */
const removeFileName = (files) => {
  // 마지막 .의 위치를 구한다.
  // 마지막 .의 위치 다음이 파일 확장자를 의미한다.
  const lastIndex = files.lastIndexOf(".");

  // 파일 이름에서 .이 존재하지 않는 경우이다.
  // 이 경우 파일 확장자가 존재하지 않는 경우를 의미한다.
  if (lastIndex < 0) {
    return "";
  }

  // substring을 함수를 이용해 확장자만 잘라준다.
  // lastIndex의 값은 마지막 .의 위치이기 때문에 해당 위치 다음부터 끝까지 문자열을 잘라준다.
  // 문자열을 자른 후 소문자로 변경시켜 확장자 값을 반환 해준다.
  return files.substring(lastIndex + 1).toLowerCase();
};
