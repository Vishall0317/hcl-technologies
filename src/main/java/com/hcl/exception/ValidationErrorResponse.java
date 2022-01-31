package com.hcl.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ValidationErrorResponse extends ErrorResponse{
	private Map<String, String> errors=new HashMap<>();
}
