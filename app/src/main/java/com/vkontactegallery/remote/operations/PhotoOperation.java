package com.vkontactegallery.remote.operations;

import android.support.annotation.NonNull;

import com.redmadrobot.chronos.ChronosOperationResult;
import com.vkontactegallery.database.DatabaseGateway;
import com.vkontactegallery.model.Photo;
import com.vkontactegallery.model.Response;
import com.vkontactegallery.remote.service.PhotosService;

import java.util.List;

/**
 * Created by Ivan Danilov on 05.11.2015.
 */
public class PhotoOperation extends AbstractOperation<List<Photo>> {

    private String userId;
    private String albumId;

    public PhotoOperation(String userId, String albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    @Override
    protected Response<List<Photo>> executeRoutine() {
        Response<List<Photo>> albums = executeService(new PhotosService(userId, albumId));
        if (albums != null) {
            DatabaseGateway.getInstance().savePhotos(albums.getData());
        }
        return albums;
    }

    @NonNull
    @Override
    public Class<? extends ChronosOperationResult<Response<List<Photo>>>> getResultClass() {
        return Result.class;
    }

    public static class Result extends ChronosOperationResult<Response<List<Photo>>> {

    }
}
