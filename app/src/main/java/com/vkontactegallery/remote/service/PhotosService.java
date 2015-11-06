package com.vkontactegallery.remote.service;

import com.vkontactegallery.model.Photo;
import com.vkontactegallery.remote.IService;

import java.util.List;

/**
 * Created by Ivan Danilov on 04.11.2015.
 */
public class PhotosService extends AbstractRestService<List<Photo>> {

    private String userId;
    private String albumId;

    public PhotosService(String userId, String albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    @Override
    protected void run(IService API) {
        serviceResponseObject = API.getPhotos(userId, albumId);
    }
}
