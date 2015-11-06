package com.vkontactegallery.remote;

import com.vkontactegallery.model.Album;
import com.vkontactegallery.model.Photo;
import com.vkontactegallery.model.Response;

import java.util.List;

import retrofit.http.POST;
import retrofit.http.Query;

public interface IService {

    @POST("/photos.getAlbums")
    Response<List<Album>> getAlbums(@Query("owner_id") String userId, @Query("need_covers") String needCovers);

    @POST("/photos.get")
    Response<List<Photo>> getPhotos(@Query("owner_id") String userId, @Query("album_id") String albumId);

}
