package br.com.diego.seeddesafiocdc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationErrorOutputResponse handleValidationError(MethodArgumentNotValidException exception) {
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getFieldErrors();
		
		return buildValidationErrors(globalErrors, fieldErrors);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ValidationErrorOutputResponse handleValidationError(BindException exception) {
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getFieldErrors();
		
		return buildValidationErrors(globalErrors, fieldErrors);
	}
	
	private ValidationErrorOutputResponse buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
		var validationErrors = new ValidationErrorOutputResponse();
		
		globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
		fieldErrors.forEach(error -> {
			var errorMessage = getErrorMessage(error);
			validationErrors.addFieldError(error.getField(), errorMessage);
		});
		
		return validationErrors;
	}
	
	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}
