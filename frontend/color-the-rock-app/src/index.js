import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import store from "./stores/store";
import { Provider } from "react-redux";
import GlobalStyles from "./styles/GlobalStyles";
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <Provider store={store}>
      <GlobalStyles />
      <App />
    </Provider>
);
