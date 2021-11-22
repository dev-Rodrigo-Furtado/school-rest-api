package com.example.school.exception.handle;

import com.example.school.exception.DocumentFoundException;
import com.example.school.exception.ValidationError;
import com.example.school.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex, HttpServletRequest req) {
		Map<String, Object> error = new HashMap<>();
		error.put("error", ex.getMessage());

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(error);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<ResponseError> handleDateTimeException(DateTimeParseException ex, HttpServletRequest req) {
		ResponseError responseError = new ResponseError();
		responseError.getErrors()
			.add(new ValidationError(ex.getParsedString(), "value could not be parsed"));

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(responseError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> handleValidationException(MethodArgumentNotValidException ex) {
		ResponseError responseError = new ResponseError();

	    ex.getBindingResult().getAllErrors().forEach(error -> {
	        String field = ((FieldError) error).getField();
	        String detail = error.getDefaultMessage();
	        responseError.getErrors().add(new ValidationError(field, detail));
	    });

	    return ResponseEntity
	    		.status(HttpStatus.BAD_REQUEST)
	    		.body(responseError);
	}

	@ExceptionHandler(DocumentFoundException.class)
	public ResponseEntity<ResponseError> handleDateTimeException(DocumentFoundException ex, HttpServletRequest req) {
		ResponseError responseError = new ResponseError();
		responseError.getErrors()
			.add(new ValidationError("document", "document already exists"));

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(responseError);
	}

}
