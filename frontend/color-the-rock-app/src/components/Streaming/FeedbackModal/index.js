import React, {
  useState,
  createRef,
  useEffect,
  useRef,
  useCallback,
} from "react";
import * as S from "./style";

const chunkSize = 1024 * 1024;

const FeedbackModal = ({ closeFeedback, session, picture = null }) => {
  const canvasRef = createRef(null);
  // const contextRef = useRef(null); // 캔버스의 드로잉 컨텍스트
  const parentRef = useRef(null);
  // const [isDrawing, setIsDrawing] = useState(false);
  const [ctx, setCtx] = useState();
  // const [lastX, setLastX] = useState(0);
  // const [lastY, setLastY] = useState(0);

  useEffect(() => {
    const canvas = canvasRef.current;
    const context = canvas.getContext("2d");
    setCtx(context);
  }, []);

  useEffect(() => {
    if (ctx) {
      const canvas = canvasRef.current;
      const parent = parentRef.current;

      const resizeCanvas = () => {
        canvas.width = parent.offsetWidth;
        canvas.height = parent.offsetHeight;
        ctx.fillRect(0, 0, canvas.width, canvas.height);
      };

      resizeCanvas();
      window.addEventListener("resize", resizeCanvas);
      return () => {
        window.removeEventListener("resize", resizeCanvas);
      };
    }
  }, [ctx]);

  useEffect(() => {
    if (picture === null || ctx === null) {
      return;
    }
    const image = new Image();
    image.src = picture;
    image.onload = function () {
      ctx.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height);
      // ctx.clearRect(
      //   0,
      //   0,
      //   document.getElementById("canvas").width,
      //   document.getElementById("canvas").height
      // );
      ctx.drawImage(
        image,
        0,
        0,
        canvasRef.current.width,
        canvasRef.current.height
      );
    };
  }, [picture, ctx]);

  const startDrawing = (event) => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");
    const rect = canvas.getBoundingClientRect();
    console.log("rect: ", rect);
    // const x = event.clientX - rect.left;
    // const y = event.clientY - rect.top;
    const x = event.clientX;
    const y = event.clientY;

    // const x = event.clientX - event.offsetX;
    // const y = event.clientY - event.offsetY;

    console.log("(x, y): ", "(", x, ", ", y + ")");
    console.log("흠, ", event.clientX, ", ", event.clientY);
    let radius = 7;
    let startAngle = 0;
    let endAngle = 2 * Math.PI;
    let counterClockwise = false;
    ctx.beginPath();
    ctx.arc(x, y, radius, startAngle, endAngle, counterClockwise);
    ctx.strokeStyle = "#8ED6FF";
    ctx.lineWidth = 2;
    ctx.stroke();
    sendDrawing();
  };

  const sendDrawing = () => {
    const canvas = document.getElementById("canvas");

    const dataURL = canvas.toDataURL();
    const base64 = dataURL;

    console.log("base64: ", base64);
    for (let i = 0; i < base64.length; i += chunkSize) {
      const chunk = base64.slice(i, i + chunkSize);
      sendData(chunk);
    }
  };

  const sendData = (data) => {
    const signalOptions = {
      data: JSON.stringify({ image: data }),
      type: "drawingSignal",
      to: [],
    };
    session.signal(signalOptions);
  };

  return (
    <S.ContainerWrap>
      <S.Container>
        <S.ContentBox ref={parentRef}>
          <S.ChromeClose
            onClick={() => {
              closeFeedback();
            }}
          />
          <canvas
            id="canvas"
            style={{
              width: "100%",
              height: "100%",
            }}
            ref={canvasRef}
            onPointerDown={startDrawing}
          />
        </S.ContentBox>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default FeedbackModal;
