package com.exalt.villaRentalSystem.errorAPI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError {

    private List<String> errors;

    private String uri;

    private Date timestamp;

    public ValidationError() {
        this.timestamp = new Date();
        this.errors = new ArrayList<>();
    }

    public void addErrors(String error){
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
