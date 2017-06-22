package br.com.fourdev.orderfood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// RequestUpgradeStrategy upgradeStrategy = new
		// TomcatRequestUpgradeStrategy();
		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
		// ((StompWebSocketEndpointRegistration)
		// registry).setHandshakeHandler(new
		// DefaultHandshakeHandler(upgradeStrategy))
		// .setAllowedOrigins("*");
	}
//
//	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//		messages
//				// message types other than MESSAGE and SUBSCRIBE
//				.nullDestMatcher().authenticated()
//				// matches any destination that starts with /rooms/
//				.simpDestMatchers("/topic/**").authenticated()
//				// (i.e. cannot send messages directly to /topic/, /queue/)
//				// (i.e. cannot subscribe to /topic/messages/* to get messages
//				// sent to
//				// /topic/messages-user<id>)
//				.simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll()
//				// catch all
//				.anyMessage().denyAll();
//	}

}