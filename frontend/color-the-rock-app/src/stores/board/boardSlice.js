import { createSlice } from "@reduxjs/toolkit";
export const boardSlice = createSlice({
  name: "board",
  initialState: {
    searchGymName: "",
    searchColorValue: "색상",
  },
  reducers: {
    setSearchGymName: (state, action) => {
      state.searchGymName = action.payload;
    },
    setSearchColorValue: (state, action) => {
      state.searchColorValue = action.payload;
    },
  },
});

export const { setSearchGymName, setSearchColorValue } = boardSlice.actions;
export default boardSlice.reducer;
