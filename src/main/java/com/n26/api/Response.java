package com.n26.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author rohitkumar
 * @param <T> Type of Body
 */
public class Response<T> {

    @JsonProperty("status_code")
    @ApiModelProperty(notes = "Transactions statistics API Http status codes.")
    private String statusCode;

    @JsonProperty("body")
    @ApiModelProperty(notes = "Transactions statistics API response body.")
    private T body;

    public Response(String statusCode, T body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
