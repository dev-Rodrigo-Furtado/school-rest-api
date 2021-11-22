package com.example.school.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Meta {

	private static final int BAD_REQUEST = 400;
	private final String timestamp;
	private final int status;

	public Meta() {
		super();
		this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toString();
		this.status = BAD_REQUEST;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

}
