package com.vkontactegallery;

import java.io.Serializable;

/**
 * Created by Ivan Danilov on 24.12.2015.
 */
public class Settings implements Serializable {

    private String msg;
    private String friend;
    private String reply;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
