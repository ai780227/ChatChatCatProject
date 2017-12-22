package com.ccc.model.service.event;

import org.springframework.context.ApplicationEvent;

public class ChatroomEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2111925629439108318L;

	public ChatroomEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
