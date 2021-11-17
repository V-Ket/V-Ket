package com.vket.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

public class MyChannelInterceptor implements ChannelInterceptor{

	@Override
	public Message preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		StompCommand command = accessor.getCommand();
		if (command.compareTo(StompCommand.SUBSCRIBE) == 0) {
			String destination = accessor.getDestination();
		} else if (command.compareTo(StompCommand.CONNECT) == 0) {
		} else if (command.compareTo(StompCommand.DISCONNECT) == 0) {
		}
		return message;
	}
}