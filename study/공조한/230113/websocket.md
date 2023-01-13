# WebSocket 개인학습
## 작성일: 23.01.13
## 작성자: 공조한

---

## 1. WebSocket

기존 단방향 HTTP 프로토콜과 호롼되어 양방향 통신을 제공하기 위해 개발된 프로토콜입니다.

일반 socket통신과 다르게 HTTP 80 port를 사용하기 때문에 방화벽에 제약이 없습니다.

접속까지는 기존 HTTP 프로토콜로 진행되고, 이후 통신은 자체적인 WebSocket 프로토콜로 통신하게 됩니다.

WebSocket 이전에는 Polling이나 Long Polling, Streaming 등의 방식으로 해결했었습니다.

- Polling: 일반적인 HTTP 요청을 반복적으로 보내, 이벤트 내용을 전달받는 방식
- Long Polling: 클라이언트가 한번 HTTP 요청을 보낸뒤에, 전달할 이벤트가 생기는 순간, 클라이언트로 응답을 보내는 방식
- Streaming: 클라이언트가 한번 HTTP 요청을 보낸 뒤에, 이벤트가 발생할 때마다 클라이언트로 메세지 보내기를 반복하는 방식

WebSocket은 클라이언트가 접속요청을 하고 웹 서버가 응답한 후 연결을 끊는 것이 아니라, Connection을 그대로 유지하고 클라이언트의 요청이 없어도 데이터를 전송할 수 있는 프로토콜입니다.

프로토콜 요청은 `ws://`으로 시작합니다.

### WebSocket 구현해보기

1. Spring boot 프로젝트에 `spring websocket`라이브러리 추가
2. WebSocket Handler 작성

    소켓 통신은 서버와 클라이언트가 1:N의 관계를 맺는다. 따라서, 하나의 서버에서 다수의 클라이언트들을 상대해야 하고, 다수의 클라이언트들이 보낸 메세지를 처리할 핸들러가 필요하다.
    실시간 텍스트 기반 채팅을 구현해보기 위해, `TextWebSocketHandler`를 상속받아서 작성한다.

3. WebSocket Config 적성

    `@EnableWebSocket` 어노테이션을 통해, WebSocket을 활성화하도록 한다.
    `setAllowedOrigins("*")`를 통해서 다른 서버에서도 접속이 가능하도록 한다.

## 2. SockJS

SockJS는 기본적으로 WebSocket을 지원하고, WebSocket을 지원하지 않는 브라우저의 경우, HTTP Streaming, HTTP polling 방식을 통해 Front-end와 Back-end 사이의 연결 및 메세지 송수신 구현을 지원해주는 WebSocket Polyfill 라이브러리입니다.

SockJS을 이용하여 `WebSocket Emulation`방식을 사용합니다.

`WebSocket Emulation`은 우선적으로 WebSocket을 시도하고, 실패하는 경우, HTTP기반의 다른 기술로 전환하여 다시 연결을 시도합니다.

백엔드 구현은
-  `node.js`을 이용하는 경우 `socket.io`를, 
-  `spring`을 이용하는 경우 `SockJS`를 이용하는 것이 일반적입니다.

적용방법은 간단합니다. 

`WebSocketConfig` 파일에서 `registry`에 `.withSockJS()`를 추가해주면 됩니다.