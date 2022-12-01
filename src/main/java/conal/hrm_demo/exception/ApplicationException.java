package conal.hrm_demo.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
@Data
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;
}



