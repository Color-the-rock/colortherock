import { createSlice } from "@reduxjs/toolkit";
export const userSlice = createSlice({
  name: "user",
  initialState: {
    nickName: "",
    isLogin: false,
    email: "",
  },
  reducers: {
    setNickName: (state, action) => {
      state.NickName += action.payload;
    },
    LogIn: (state, action) => {
      state.nickName = action.payload.nickName;
      state.isLogin = true;
    },
    LogOut: (state) => {
      state.nickName = "";
      state.isLogin = false;
    },
  },
});

export const { setUserName, LogIn, LogOut } = userSlice.actions;

export default userSlice.reducer;
