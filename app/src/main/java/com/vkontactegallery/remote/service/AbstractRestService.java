package com.vkontactegallery.remote.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vkontactegallery.Environment;
import com.vkontactegallery.model.Response;
import com.vkontactegallery.remote.IService;

import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;


public abstract class AbstractRestService<T> {
    private static IService iRestService;

    protected Response<T> serviceResponseObject;

    protected static IService getAPI() {
        System.setProperty("http.keepAlive", "false");
        if (iRestService == null) {
            iRestService = new RestAdapter.Builder()
                    .setEndpoint(Environment.SERVER)
                    .setConverter(new JacksonConverter(getObjectMapper()))
                    .setLogLevel(Environment.LOG_LEVEL)
                    .build()
                    .create(IService.class);
        }
        return iRestService;
    }

    public Response<T> getServiceResponseObject() {
        return serviceResponseObject;
    }

    protected static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }

    public void run() {
        run(getAPI());
    }

    protected abstract void run(IService API);
}
