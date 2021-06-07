package net.apimessages.pd2.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MessageExceptionHandler extends ResponseEntityExceptionHandler{
	
	 @ExceptionHandler(Throwable.class)
	 @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        MessageErrorResponse error = new MessageErrorResponse("Server Error", details);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
	@ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Object> handleMessageNotFoundException(MessageNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        MessageErrorResponse error = new MessageErrorResponse("Message Not Found", details);
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        MessageErrorResponse error = new MessageErrorResponse("Validation Failed", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

}
