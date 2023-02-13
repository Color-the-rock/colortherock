import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import userReducer from "./users/userSlice";
import streamingReducer from "./streaming/streamingSlice";
import recordReducer from "./record/recordSlice";
import reducer from "./reducers";
export default configureStore({
  reducer,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});

// export default configureStore({
//   reducer: {
//     user: userReducer,
//     streaming: streamingReducer,
//     record: recordReducer,
//   },
//   middleware: getDefaultMiddleware({
//     serializableCheck: false,
//   }),
// });
