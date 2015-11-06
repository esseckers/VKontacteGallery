package com.vkontactegallery.remote.service;

import com.vkontactegallery.model.Album;
import com.vkontactegallery.remote.IService;

import java.util.List;

/**
 * Created by Ivan Danilov on 04.11.2015.
 */
public class AlbumsService extends AbstractRestService<List<Album>> {

    private final static String NEED_COVERS = "1";

    private String userId;

    public AlbumsService(String userId) {
        this.userId = userId;
    }

    @Override
    protected void run(IService API) {
        serviceResponseObject = API.getAlbums(userId, NEED_COVERS);
    }
}
