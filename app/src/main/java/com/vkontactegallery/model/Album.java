package com.vkontactegallery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ivan Danilov on 03.11.2015.
 */
public class Album extends RealmObject implements Serializable {

    @PrimaryKey
    private long aid;

    @JsonProperty("thumb_id")
    private String thumbId;
    private String owner_id;
    private String title;
    private String description;

    private String created;
    private String updated;

    private int size;

    @JsonProperty("thumb_src")
    private String imgThumbLink;

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getThumbId() {
        return thumbId;
    }

    public void setThumbId(String thumbId) {
        this.thumbId = thumbId;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImgThumbLink() {
        return imgThumbLink;
    }

    public void setImgThumbLink(String imgThumbLink) {
        this.imgThumbLink = imgThumbLink;
    }
}
