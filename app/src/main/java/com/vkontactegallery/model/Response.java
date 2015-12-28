package com.vkontactegallery.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ivan Danilov on 03.11.2015.
 */
public class Response<T> {

    @JsonProperty("response")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
