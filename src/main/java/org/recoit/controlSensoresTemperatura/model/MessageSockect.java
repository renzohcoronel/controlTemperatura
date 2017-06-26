package org.recoit.controlSensoresTemperatura.model;

public class MessageSockect {

	String text;
	
	public MessageSockect() {}
		

	public MessageSockect(String text) {
		this();
		this.text = text;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

		
	
}
