package com.example.school.response;

import java.util.ArrayList;
import java.util.List;

import com.example.school.dto.Meta;
import com.example.school.exception.ValidationError;

public class ResponseError {

	private final List<ValidationError> errors;
	private final Meta meta;

	public ResponseError() {
		super();
		this.errors = new ArrayList<>();
		this.meta = new Meta();
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public Meta getMeta() {
		return meta;
	}

}
