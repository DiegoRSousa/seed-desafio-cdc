package br.com.diego.seeddesafiocdc;

public class FieldErrorOutputResponse {

	private String field;
	private String message;
	
	public FieldErrorOutputResponse() {}

	public FieldErrorOutputResponse(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
