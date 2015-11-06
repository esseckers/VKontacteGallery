package com.vkontactegallery.remote.operations;

import android.support.annotation.NonNull;

import com.redmadrobot.chronos.ChronosOperationResult;
import com.vkontactegallery.TheApplication;
import com.vkontactegallery.database.DatabaseGateway;
import com.vkontactegallery.model.Album;
import com.vkontactegallery.model.Response;
import com.vkontactegallery.remote.service.AlbumsService;

import java.util.List;

/**
 * Created by Ivan Danilov on 05.11.2015.
 */
public class AlbumOperation extends AbstractOperation<List<Album>> {

    private String userId;

    public AlbumOperation(String userId) {
        this.userId = userId;
    }

    @Override
    protected Response<List<Album>> executeRoutine() {
        Response<List<Album>> albums = executeService(new AlbumsService(userId));
        if (albums != null) {
            DatabaseGateway.getInstance().saveAlbums(albums.getData());
        }
        TheApplication.getInstance().setUserId(userId);
        return albums;
    }

    @NonNull
    @Override
    public Class<? extends ChronosOperationResult<Response<List<Album>>>> getResultClass() {
        return Result.class;
    }

    public static class Result extends ChronosOperationResult<Response<List<Album>>> {

    }
}
