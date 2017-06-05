package br.com.fourdev.orderfood.config;

import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by nick on 30/09/2015.
 */
public class ClientWebSocketConfig {

	private static Logger logger = Logger.getLogger(ClientWebSocketConfig.class);

	private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

	public static StompSession stompSession;
	public static ClientWebSocketConfig helloClient;

	public ListenableFuture<StompSession> connect() {

		Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
		List<Transport> transports = Collections.singletonList(webSocketTransport);

		SockJsClient sockJsClient = new SockJsClient(transports);
		sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

		String url = "ws://{host}:{port}/gs-guide-websocket";
		return stompClient.connect(url, headers, new MyHandler(), "localhost", 9090);

	}

	public void subscribeGreetings(StompSession stompSession) throws ExecutionException, InterruptedException {
		stompSession.subscribe("/topic/greetings", new StompFrameHandler() {

			public Type getPayloadType(StompHeaders stompHeaders) {
				return byte[].class;
			}

			public void handleFrame(StompHeaders stompHeaders, Object o) {
				logger.info("Received greeting " + new String((byte[]) o));
			}
		});
	}

	public void sendHello(StompSession stompSession, String mensagem) {
		// String jsonHello = "{ \"name\" : \"Nick\" }";
		String jsonHello = "{ \"name\" : \"" + mensagem + "\" }";
		stompSession.send("/app/hello", jsonHello.getBytes());
	}

	private class MyHandler extends StompSessionHandlerAdapter {
		public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
			logger.info("Now connected");
		}
	}

	public static StompSession conectaOuRetornaWebSocket() throws InterruptedException, ExecutionException {
		if (stompSession == null) {
			helloClient = new ClientWebSocketConfig();
			ListenableFuture<StompSession> f = helloClient.connect();
			stompSession = f.get();
		}
		return stompSession;
	}

	public static void desconectarWebSocket() {
		stompSession.disconnect();
	}

	public static void main(String[] args) throws Exception {
		ClientWebSocketConfig helloClient = new ClientWebSocketConfig();

		ListenableFuture<StompSession> f = helloClient.connect();
		StompSession stompSession = f.get();

		logger.info("Subscribing to greeting topic using session " + stompSession);
		helloClient.subscribeGreetings(stompSession);

		logger.info("Sending hello message" + stompSession);
		helloClient.sendHello(stompSession, "teste " + stompSession.getSessionId());
		Thread.sleep(60000);

	}

}






//
//@Component
//public class SocketHandler extends TextWebSocketHandler {
//
//    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message)
//            throws InterruptedException, IOException {
//        for(WebSocketSession webSocketSession : sessions) {
//            Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
//            webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
//        }
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        //the messages will be broadcasted to all users.
//        sessions.add(session);
//    }
//
//}