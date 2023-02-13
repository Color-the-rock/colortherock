import React from "react";
import * as S from "./style";
import PropTypes from "prop-types";
import { useInput } from "../../../hooks/useInput";
import boardApi from "../../../api/board";
import { useParams } from "react-router-dom";
const ReportModal = ({ setOpenBoardReportModal }) => {
  const { id } = useParams();
  const [selectedRadioValue, onChangeSelectedRadioValue] = useInput(undefined);
  const handleReportBoard = () => {
    const requestBody = {
      videoBoardId: id,
      category: selectedRadioValue,
    };
    boardApi
      .reportVideoBoard(requestBody)
      .then(({ data: { status } }) => {
        if (status === 200) {
          alert("해당 게시글이 신고되었습니다.");
          setOpenBoardReportModal(false);
        }
      })
      .catch((error) => console.log(error));
  };
  return (
    <S.ModalBackground>
      <S.ModalContainer>
        <S.CloseButton onClick={() => setOpenBoardReportModal(false)} />
        <S.Title>게시글 신고</S.Title>
        <S.ReportList>
          <S.ReportItem>
            <S.RadioButton
              id="type-a"
              value="TYPE_A"
              type="radio"
              name="report-type"
              onChange={onChangeSelectedRadioValue}
            />
            <S.RadioLabel htmlFor="type-a">성공영상 아님</S.RadioLabel>
          </S.ReportItem>
          <S.ReportItem>
            <S.RadioButton
              id="type-b"
              value="TYPE_B"
              type="radio"
              name="report-type"
              onChange={onChangeSelectedRadioValue}
            />
            <S.RadioLabel htmlFor="type-b">부적절한 영상</S.RadioLabel>
          </S.ReportItem>
          <S.ReportItem>
            <S.RadioButton
              id="type-c"
              value="TYPE_C"
              type="radio"
              name="report-type"
              onChange={onChangeSelectedRadioValue}
            />
            <S.RadioLabel htmlFor="type-c">부적절한 제목</S.RadioLabel>
          </S.ReportItem>
        </S.ReportList>
        <S.Button
          onClick={handleReportBoard}
          disabled={selectedRadioValue === "" && "disabled"}
        >
          신고하기
        </S.Button>
      </S.ModalContainer>
    </S.ModalBackground>
  );
};

export default ReportModal;

ReportModal.propTypes = {
  setOpenBoardReportModal: PropTypes.func,
};
