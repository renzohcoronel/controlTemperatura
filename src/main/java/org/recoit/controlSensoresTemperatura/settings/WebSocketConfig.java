package org.recoit.controlSensoresTemperatura.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	private static Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

	
	  @Override
	    public void configureMessageBroker(MessageBrokerRegistry config) {
		  System.out.println("configuro un endpoir socket");
	        config.enableSimpleBroker("/topic");
	        config.setApplicationDestinationPrefixes("/app");
	    }

	    @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	    	System.out.println("Registro un endpoir socket");
	    	registry.addEndpoint("/sensoresSocket").setAllowedOrigins("*");
	    	registry.addEndpoint("/sensoresSocket").setAllowedOrigins("*").withSockJS();
	    }
	    

	    @Override
	    public void configureClientOutboundChannel(ChannelRegistration registration) {
	        registration.taskExecutor().corePoolSize(8);
	    }

	    @Override
	    public void configureClientInboundChannel(ChannelRegistration registration) {
	        registration.taskExecutor().corePoolSize(8);
	    }
	
	    
}
