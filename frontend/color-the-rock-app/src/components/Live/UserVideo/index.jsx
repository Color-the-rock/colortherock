import React from "react";
import OpenViduVideoComponent from "../OpemViduVideo";
import "./UserVideo.css";

function UserVideoComponent({ streamManager }) {
  const getNicknameTag = () => {
    return JSON.parse(streamManager.stream.connection.data).clientData;
  };

  return (
    <div>
      {streamManager !== undefined ? (
        <div className="streamcomponent">
          <OpenViduVideoComponent streamManager={streamManager} />
          <div>
            <p>{getNicknameTag()}</p>
          </div>
        </div>
      ) : (
        <div>streamManager is undefined</div>
      )}
    </div>
  );
}

export default UserVideoComponent;
