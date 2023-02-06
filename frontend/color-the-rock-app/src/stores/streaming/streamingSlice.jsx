import { createSlice } from "@reduxjs/toolkit";
export const streamingSlice = createSlice({
  name: "streaming",
  initialState: {
    ov: null, // openVidu 객체
    info: {
      title: "",
      roomName: "",
      drawingBackground: null,
    },
  },
  reducers: {
    setOV: (state, action) => {
      state.ov = action.payload.ov;
    },
  },
});

export const { setOV } = streamingSlice.actions;

export default streamingSlice.reducer;
