import { createSlice } from "@reduxjs/toolkit";
export const recordSlice = createSlice({
  name: "record",
  initialState: {
    currentDate: new Date(),
  },
  reducers: {
    setCurrentDate: (state, action) => {
      console.log("setCurrentDate()... ", action);
      state.currentDate = action.payload;
    },
  },
});

export const { setCurrentDate } = recordSlice.actions;
export default recordSlice.reducer;
