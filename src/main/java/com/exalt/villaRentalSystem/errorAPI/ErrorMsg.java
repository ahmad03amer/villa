package com.exalt.villaRentalSystem.errorAPI;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorMsg {
    private Object error;
    private String uri;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorMsg() {
        this.timestamp = new Date();
    }

    public ErrorMsg(String message, String uri) {
        this();
        this.error = message;
        this.uri = uri;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String url) {
        this.uri = url;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
