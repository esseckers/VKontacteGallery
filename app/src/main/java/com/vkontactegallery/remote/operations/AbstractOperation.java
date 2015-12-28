package com.vkontactegallery.remote.operations;

import com.redmadrobot.chronos.ChronosOperation;
import com.vkontactegallery.model.Response;
import com.vkontactegallery.remote.service.AbstractRestService;

/**
 * Created by Ivan Danilov on 05.11.2015.
 */
public abstract class AbstractOperation<T> extends ChronosOperation<Response<T>> {

    @Override
    public Response<T> run() {
        return executeRoutine();
    }

    protected Response<T> executeRoutine() {
        return null;
    }

    public Response<T> executeService(AbstractRestService<T> abstractRPCService) {
        abstractRPCService.run();
        return abstractRPCService.getServiceResponseObject();
    }
}
