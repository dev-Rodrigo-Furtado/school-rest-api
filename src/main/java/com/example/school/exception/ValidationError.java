package com.example.school.exception;

public class ValidationError {

	private String field;
	private String detail;

	public ValidationError(String field, String detail) {
		super();
		this.field = field;
		this.detail = detail;
	}

	public String getField() {
		return field;
	}

	public String getDetail() {
		return detail;
	}

}
