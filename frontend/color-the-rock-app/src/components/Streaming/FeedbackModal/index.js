import React, {
  useState,
  createRef,
  useEffect,
  useRef,
  useCallback,
} from "react";
import * as S from "./style";

const chunkSize = 1024 * 1024;

const FeedbackModal = ({ session, picture = null }) => {
  const canvasRef = createRef(null);
  const contextRef = useRef(null); // 캔버스의 드로잉 컨텍스트

  const [ctx, setCtx] = useState();
  const [lastX, setLastX] = useState(0);
  const [lastY, setLastY] = useState(0);

  useEffect(() => {
    const canvas = canvasRef.current;
    canvas.width = document.getElementById("canvas").width;
    canvas.height = document.getElementById("canvas").height;
    const context = canvas.getContext("2d");
    context.strokeStyle = "black";
    context.lineWidth = 2.5;
    contextRef.current = context;
    setCtx(context);
  }, []);

  useEffect(() => {
    if (picture === null || ctx === null) {
      return;
    }
    const image = new Image();
    image.src = picture;
    image.onload = function () {
      ctx.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height);
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
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    ctx.fillRect(x - 2, y - 2, 4, 4);
    setLastX(x);
    setLastY(y);
    sendDrawing();
  };

  const draw = (event) => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    // ctx.fillRect(x - 2, y - 2, 10, 10);
    ctx.strokeRect(x - 5, y - 5, 10, 10);
    setLastX(x);
    setLastY(y);
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
        <S.ContentBox>
          <canvas
            id="canvas"
            ref={canvasRef}
            // width={200}
            // height={500}
            onPointerDown={startDrawing}
          />
        </S.ContentBox>
      </S.Container>
    </S.ContainerWrap>
  );
};

export default FeedbackModal;
