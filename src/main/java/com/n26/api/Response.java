package com.n26.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * @author rohitkumar
 * @param <T> Type of Body
 */
public class Response<T> {

    @JsonProperty("status_code")
    @ApiModelProperty(notes = "Transactions statistics API Http status codes.")
    private int statusCode;

    @JsonProperty("body")
    @ApiModelProperty(notes = "Transactions statistics API response body.")
    private T body;

    public Response() {
    }

    public Response(int statusCode, T body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", body=" + body +
                '}';
    }
}
