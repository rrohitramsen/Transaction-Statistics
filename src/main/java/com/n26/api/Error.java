package com.n26.api;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * @author rohitkumar
 */
public class Error {

    @ApiModelProperty(value = "status_code")
    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    public Error() {
    }

    public Error(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
