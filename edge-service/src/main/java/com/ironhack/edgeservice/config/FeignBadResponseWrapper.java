package com.ironhack.edgeservice.config;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.http.HttpHeaders;


public class FeignBadResponseWrapper extends HystrixBadRequestException {
    private final int status;
    private final HttpHeaders headers;
    private final String body;

    public FeignBadResponseWrapper(int status, HttpHeaders headers, String body) {
        super(body/*.split(",")[0].split(":")[1]*/);
        this.status = status;
        this.headers = headers;
        this.body = body;
        //.split(",")[0].split(":")[1];
    }

    public int getStatus() {
        return status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}