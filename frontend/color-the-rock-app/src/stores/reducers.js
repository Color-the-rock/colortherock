import { combineReducers } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage/session";
import record from "./record/recordSlice";
import streaming from "./streaming//streamingSlice";
import users from "./users/userSlice";
import board from "./board/boardSlice";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["users"],
};

const reducers = combineReducers({
  users,
  streaming,
  record,
  board,
});

export default persistReducer(persistConfig, reducers);
