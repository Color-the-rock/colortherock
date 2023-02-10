import { createSlice } from "@reduxjs/toolkit";
export const recordSlice = createSlice({
  name: "record",
  initialState: {
    currentDate: new Date(),
    videos: [],
  },
  reducers: {
    setCurrentDate: (state, action) => {
      console.log("setCurrentDate()... ", action);
      state.currentDate = action.payload;
    },

    setVideos: (state, action) => {
      console.log("setVideos()... ", action);
      state.videos = action.payload;
    },
  },
});

export const { setCurrentDate } = recordSlice.actions;
export default recordSlice.reducer;
