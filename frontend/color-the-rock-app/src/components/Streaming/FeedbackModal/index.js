// import React, {
//   useState,
//   createRef,
//   useEffect,
//   useRef,
//   useCallback,
// } from "react";
// import * as S from "./style";

// const FeedbackModal = ({ session }) => {
//   const canvasRef = createRef(null);
//   const contextRef = useRef(null); // 캔버스의 드로잉 컨텍스트

//   const [ctx, setCtx] = useState();
//   const [isDrawing, setIsDrawing] = useState(false);

//   useEffect(() => {
//     const canvas = canvasRef.current;
//     canvas.width = window.innerWidth;
//     canvas.height = window.innerHeight;

//     const context = canvas.getContext("2d");
//     context.strokeStyle = "black";
//     context.lineWidth = 2.5;
//     contextRef.current = context;

//     setCtx(context);
//   }, []);

//   const sendDrawing = () => {
//     const canvas = document.getElementById("canvas");
//     const dataURL = canvas.toDataURL();
//     const base64 = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
//     if (session !== undefined) {
//       const signalOptions = {
//         data: JSON.stringify({ image: base64 }),
//         type: "drawingSignal",
//         to: [],
//       };
//       session.signal(signalOptions);
//     }
//   };

//   const startDrawing = () => {
//     setIsDrawing(true);
//   };

//   const finishDrawing = () => {
//     setIsDrawing(false);
//     sendDrawing();
//   };

//   const drawing = ({ nativeEvent }) => {
//     const { offsetX, offsetY } = nativeEvent;

//     if (ctx) {
//       if (!isDrawing) {
//         ctx.beginPath();
//         ctx.moveTo(offsetX, offsetY);
//       } else {
//         ctx.lineTo(offsetX, offsetY);
//         ctx.stroke();
//       }
//     }
//   };

//   const onClickHandler = () => {};

//   return (
//     <S.ContainerWrap>
//       <S.Container>
//         <S.ContentBox>
//           <ov-videoconference>
//             <canvas
//               id="canvas"
//               style={{
//                 zIndex: "5000",
//               }}
//               ref={canvasRef}
//               onMouseDown={startDrawing}
//               onMouseUp={finishDrawing}
//               onMouseMove={drawing}
//               onMouseLeave={finishDrawing}
//             />
//           </ov-videoconference>
//         </S.ContentBox>
//       </S.Container>
//     </S.ContainerWrap>
//   );
// };

// export default FeedbackModal;
