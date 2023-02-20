package com.course.app.core;

import com.course.app.web.util.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class Message implements Comparable<Message>{
	private String text;

	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime time;

	public Message(String text, LocalDateTime time) {
		this.text = text;
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public int compareTo(Message o) {
		if (this.time.compareTo(o.getTime()) > 0){
			return 1;
		} else if (this.time.compareTo(o.getTime()) < 0) {
			return -1;
		}

		return 0;
	}
}
