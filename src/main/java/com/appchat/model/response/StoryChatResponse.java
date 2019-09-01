package com.appchat.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "story_chat")
public class StoryChatResponse {
    @Id
    private int id;
   @Column(name = "friend_id")
   private int friend_id;
   @Column(name = "friend_avatar")
    private String friend_avatar;
   @Column(name = "friend_image")
    private String friend_image;
    @Column(name = "friend_nameofchat")
    private String friend_nameofchat;
    @Column(name = "created_time")
    private Date createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public String getFriend_avatar() {
        return friend_avatar;
    }

    public void setFriend_avatar(String friend_avatar) {
        this.friend_avatar = friend_avatar;
    }

    public String getFriend_image() {
        return friend_image;
    }

    public void setFriend_image(String friend_image) {
        this.friend_image = friend_image;
    }
    public String getFriend_nameofchat() {
        return friend_nameofchat;
    }

    public void setFriend_nameofchat(String friend_nameofchat) {
        this.friend_nameofchat = friend_nameofchat;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
