package com.hcl.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
	private String message;
	private int statuscode;
	private LocalDateTime dateTime;
}
