import { combineReducers } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import record from "./record/recordSlice";
import streaming from "./streaming//streamingSlice";
import users from "./users/userSlice";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["users", "streaming"],
};

const reducers = combineReducers({
  users,
  streaming,
  record,
});

export default persistReducer(persistConfig, reducers);
