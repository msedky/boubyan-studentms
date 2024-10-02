package org.boubyan.studentms.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5549995516305978065L;

	private HttpStatus status;

	public BusinessException(String message) {
		super(message);
		this.status = HttpStatus.BAD_REQUEST;
	}

	public BusinessException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

}
