package com.koylumuhendis.librarymanagement.exception;

import com.koylumuhendis.librarymanagement.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String errorMessage;
	private final ErrorCode code;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public ErrorCode getCode() {
		return code;
	}

	public static class builder{
		private HttpStatus httpStatus;
		private String errorMessage;
		private ErrorCode code;
		
		public builder httpStatus(HttpStatus httpStatus) {
			this.httpStatus=httpStatus;
			return this;
		}
		public builder errorMessage(String errorMessage) {
			this.errorMessage=errorMessage;
			return this;
		}
		public builder code(ErrorCode code) {
			this.code=code;
			return this;
		}
		public GenericException build() {
			return new GenericException(this);
		}
	}
	public GenericException(builder builder) {
		this.httpStatus=builder.httpStatus;
		this.errorMessage=builder.errorMessage;
		this.code=builder.code;
	}

}
