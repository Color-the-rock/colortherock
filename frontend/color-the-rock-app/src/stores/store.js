import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./users/userSlice";
export default configureStore({
  reducer: {
    user: userReducer,
  },
});
