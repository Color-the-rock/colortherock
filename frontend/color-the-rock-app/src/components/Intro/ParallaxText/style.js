import styled from "styled-components";
export const Parallax = styled.div`
  overflow: hidden;
  letter-spacing: -2px;
  line-height: 0.8;
  margin: 0;
  white-space: nowrap;
  display: flex;
  flex-wrap: nowrap;

  .scroller {
    font-weight: 600;
    text-transform: uppercase;
    font-size: 9rem;
    font-family: "Poppins";
    font-style: italic;
    display: flex;
    white-space: nowrap;
    display: flex;
    flex-wrap: nowrap;
    margin-bottom: 1rem;
    @media (max-width: 992px) {
      font-size: 7rem;
    }
  }

  span {
    display: block;
    margin-right: 30px;
  }
`;
