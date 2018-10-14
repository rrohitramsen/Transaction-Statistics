package com.n26.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author rohitkumar
 * @param <T> Type of body.
 */
public class Request<T> {

    @JsonProperty("body")
    @ApiModelProperty(notes = "Transactions statistics API response body.")
    private T body;

    public Request(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
