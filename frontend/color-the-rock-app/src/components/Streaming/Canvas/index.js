import React, { createRef, useEffect } from "react";

const Canvas = ({ videoRef }) => {
  const canvasRef = createRef(null);

  useEffect(() => {
    if (canvasRef.current && videoRef.current) {
      const interval = setInterval(() => {
        const ctx = canvasRef.current.getContext("2d");
        ctx.drawImage(videoRef.current, 0, 0, 250, 188);
      }, 100);
      return () => clearInterval(interval);
    }
  });

  return <canvas ref={canvasRef} width="250" height="188"></canvas>;
};

export default React.memo(Canvas);
