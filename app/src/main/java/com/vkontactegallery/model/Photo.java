package com.vkontactegallery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ivan Danilov on 03.11.2015.
 */
public class Photo extends RealmObject implements Serializable {

    private long id;

    @PrimaryKey
    @JsonProperty("album_id")
    private long thumbId;

    @JsonProperty("photo_75")
    private String photo75;

    @JsonProperty("photo_130")
    private String photo130;

    @JsonProperty("photo_604")
    private String photo604;

    @JsonProperty("photo_807")
    private String photo807;

    @JsonProperty("photo_2560")
    private String photo2560;

    @JsonProperty("photo_1280")
    private String photo1280;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getThumbId() {
        return thumbId;
    }

    public String getPhoto75() {
        return photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public String getPhoto2560() {
        return photo2560;
    }

    public String getPhoto1280() {
        return photo1280;
    }

    public void setThumbId(long thumbId) {
        this.thumbId = thumbId;
    }

    public void setPhoto75(String photo75) {
        this.photo75 = photo75;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public void setPhoto604(String photo604) {
        this.photo604 = photo604;
    }

    public void setPhoto807(String photo807) {
        this.photo807 = photo807;
    }

    public void setPhoto2560(String photo2560) {
        this.photo2560 = photo2560;
    }

    public void setPhoto1280(String photo1280) {
        this.photo1280 = photo1280;
    }
}
