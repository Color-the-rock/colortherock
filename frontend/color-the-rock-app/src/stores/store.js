import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import userReducer from "./users/userSlice";
import streamingReducer from "./streaming/streamingSlice";
export default configureStore({
  reducer: {
    user: userReducer,
    streaming: streamingReducer,
  },
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});
