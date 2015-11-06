package com.vkontactegallery.database;

import com.vkontactegallery.TheApplication;
import com.vkontactegallery.model.Album;
import com.vkontactegallery.model.Photo;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Ivan Danilov on 03.11.2015.
 */
public class DatabaseGateway {

    private static Realm realm;
    private static DatabaseGateway instance;

    public static DatabaseGateway getInstance() {
        if (instance == null) {
            instance = new DatabaseGateway();
        }
        realm = Realm.getInstance(TheApplication.getInstance());
        return instance;
    }

    public List<Album> getAlbums(Realm realm) {
        List<Album> albumList;
        realm.beginTransaction();
        albumList = realm.where(Album.class).findAll();
        realm.commitTransaction();
        return albumList;
    }

    public List<Photo> getPhotos(Realm realm) {
        List<Photo> photoList;
        realm.beginTransaction();
        photoList = realm.where(Photo.class).findAll();
        realm.commitTransaction();
        realm.close();
        return photoList;
    }

    public void saveAlbums(final List<Album> albums) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(albums);
        realm.commitTransaction();
        realm.close();
    }

    public void savePhotos(final List<Photo> photos) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(photos);
        realm.commitTransaction();
        realm.close();
    }
}
