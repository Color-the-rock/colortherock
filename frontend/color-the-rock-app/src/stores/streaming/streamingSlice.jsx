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
    userOpenViduToken: "",
  },
  reducers: {
    setOV: (state, action) => {
      console.log("action : ", action.payload.ov);
      state.ov = action.payload.ov;
    },
    setOpenViduToken: (state, action) => {
      state.userOpenViduToken = action.payload;
    },
  },
});

export const { setOV, setOpenViduToken } = streamingSlice.actions;

export default streamingSlice.reducer;
