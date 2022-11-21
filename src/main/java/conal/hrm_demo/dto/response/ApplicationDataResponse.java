package conal.hrm_demo.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationDataResponse<T> {
    private HttpStatus status;
    private int errorCode;
    private String message;
    private T data;


    public ApplicationDataResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errorCode = this.status.value();
    }

    public ApplicationDataResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
        this.errorCode = this.status.value();
    }
}
