package conal.hrm_demo.exception;

import conal.hrm_demo.dto.response.ApplicationExceptionResponse;
import conal.hrm_demo.exception.domain.EmailNotFoundException;
import conal.hrm_demo.exception.domain.UserNotFoundException;
import conal.hrm_demo.exception.domain.UsernameExistException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    private static final String ERROR_PATH = "/error";

//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<HttpResponse> accountDisabledException() {
//        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<HttpResponse> badCredentialsException() {
//        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<HttpResponse> accessDeniedException() {
//        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
//    }
//
//    @ExceptionHandler(LockedException.class)
//    public ResponseEntity<HttpResponse> lockedException() {
//        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<HttpResponse> tokenExpiredException(JwtException exception) {
//        return createHttpResponse(UNAUTHORIZED, exception.getMessage().toUpperCase());
//    }


    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<ApplicationExceptionResponse> usernameExistException(UsernameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionResponse> usernameExistException(ApplicationException exception) {
        return createHttpResponse(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApplicationExceptionResponse> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApplicationExceptionResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApplicationExceptionResponse> noHandlerFoundException(NoHandlerFoundException exception) {
        return createHttpResponse(BAD_REQUEST, "There is no page for a " + exception.getHttpMethod() + " request on path " + exception.getRequestURL());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApplicationExceptionResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationExceptionResponse> internalServerErrorException(Exception exception) {
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ApplicationExceptionResponse> notFoundException(NoResultException exception) {
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApplicationExceptionResponse> iOException(IOException exception) {
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    private ResponseEntity<ApplicationExceptionResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new ApplicationExceptionResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<ApplicationExceptionResponse> pageNotFound() {
        return createHttpResponse(NOT_FOUND, "This page doesn't exist");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationExceptionResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, "Field " + fieldName + errorMessage.replace("{fieldName}", ""));
        });
        return createHttpResponse(BAD_REQUEST, "");
    }

}
