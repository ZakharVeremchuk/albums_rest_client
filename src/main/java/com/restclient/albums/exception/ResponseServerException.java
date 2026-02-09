package com.restclient.albums.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class ResponseServerException extends RuntimeException {

    public ResponseServerException(HttpStatusCode statusCode, HttpHeaders headers) {
        super("Server return code is: " + statusCode.value() + ", headers: " + headers.values());
    }
}
