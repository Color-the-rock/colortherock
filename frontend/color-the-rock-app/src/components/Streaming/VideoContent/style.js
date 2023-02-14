import styled from "styled-components";
import { HiOutlineDocumentText, HiDownload } from "react-icons/hi";
import { FiPlay } from "react-icons/fi";

export const Container = styled.div`
  width: 100%;
  height: 5rem;
  padding: 0.1rem;
  border-radius: 10px;
  margin-bottom: 10px;
  /* border: 1.5px solid transparent;
  background-image: linear-gradient(
      var(--color-background),
      var(--color-background)
    ),
    linear-gradient(to right, #ff6cab, #8533ff);
  background-origin: border-box;
  background-clip: content-box, padding-box, border-box; */
  display: grid;
  grid-template-columns: 1.5fr 5fr 2fr;
  grid-gap: 0.5rem;
`;

export const PlayButtonWrap = styled.span`
  display: flex;
  height: 100%;
  align-items: center;
`;

export const ContentWrap = styled.span`
  display: flex;
  height: 100%;
  align-items: center;
`;

export const Content = styled.span`
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 1fr 1fr;
`;

export const RowContent = styled.div`
  height: 100%;
`;

export const DocumentText = styled(HiOutlineDocumentText)`
  font-size: 2.5rem;
`;

export const DownLoadWrap = styled.a``;

export const DownLoadButton = styled(HiDownload)`
  margin-left: 5px;
  margin-right: 5px;
  font-size: 2rem;
`;

export const PlayButton = styled(FiPlay)`
  margin-left: 5px;
  margin-right: 5px;
  font-size: 2rem;
`;

export const DurationWrap = styled.span`
  display: flex;
  text-align: center;
  height: 100%;
  border-radius: 5px;
  display: flex;
  align-items: center;
  padding: 1px 10px 1px 10px;
  background-color: gray;
`;
