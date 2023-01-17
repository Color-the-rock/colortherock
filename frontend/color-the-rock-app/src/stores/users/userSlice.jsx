import { createSlice } from "@reduxjs/toolkit";
export const userSlice = createSlice({
  name: "user",
  initialState: {
    userName: "",
  },
  reducers: {
    setUserName: (state, action) => {
      state.userName += action.payload;
    },
  },
});

export const { setUserName } = userSlice.actions;

export default userSlice.reducer;
