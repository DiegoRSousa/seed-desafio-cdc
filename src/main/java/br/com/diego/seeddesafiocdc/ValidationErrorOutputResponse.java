package br.com.diego.seeddesafiocdc;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorOutputResponse {

	List<String> globalErrorMessages = new ArrayList<>();
	List<FieldErrorOutputResponse> fieldErrors = new ArrayList<>();
	
	public void addError(String message) {
		globalErrorMessages.add(message);
	}
	
	public void addFieldError(String field, String message) {
		FieldErrorOutputResponse fieldError = new FieldErrorOutputResponse(field, message);
		fieldErrors.add(fieldError);
	}
	
	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}
	
	public List<FieldErrorOutputResponse> getErrors() {
		return fieldErrors;
	}
}
