import { createSlice } from "@reduxjs/toolkit";

// info 객체 내용
// info: {
//   title: "",
//   gymName: "",
//   drawingBackground: null,
// },

export const streamingSlice = createSlice({
  name: "streaming",
  initialState: {
    ov: null, // openVidu 객체
    info: {},
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
    setStreamingInfo: (state, action) => {
      state.info = action.payload;
    },
  },
});

export const { setOV, setOpenViduToken, setStreamingInfo } =
  streamingSlice.actions;

export default streamingSlice.reducer;
