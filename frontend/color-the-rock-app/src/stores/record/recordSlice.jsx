import { createSlice } from "@reduxjs/toolkit";
export const recordSlice = createSlice({
  name: "record",
  initialState: {
    currentDate: new Date(),
    videos: [],
    isSuccess: true,
  },
  reducers: {
    setCurrentDate: (state, action) => {
      state.currentDate = action.payload;
    },

    setVideos: (state, action) => {
      state.videos = action.payload;
    },

    setSuccess: (state, action) => {
      state.isSuccess = action.payload;
    },
  },
});

export const { setCurrentDate, setVideos, setSuccess } = recordSlice.actions;
export default recordSlice.reducer;
