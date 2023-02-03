import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import userReducer from "./users/userSlice";
import streamingReducer from "./streaming/streamingSlice";
import recordReducer from "./record/recordSlice";
export default configureStore({
  reducer: {
    user: userReducer,
    streaming: streamingReducer,
    record: recordReducer,
  },
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});