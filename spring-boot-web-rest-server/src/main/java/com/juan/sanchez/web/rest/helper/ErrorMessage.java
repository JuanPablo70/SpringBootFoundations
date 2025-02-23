package com.juan.sanchez.web.rest.helper;

public class ErrorMessage {

	private final String content;
	private final String dateTime;

	public ErrorMessage(String content, String dateTime) {
		this.content = content;
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public String getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
        return "ErrorMessage [content=" +
                content +
                ", dateTime=" +
                dateTime +
                "]";
	}

}
