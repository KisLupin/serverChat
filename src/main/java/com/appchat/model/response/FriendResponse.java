package com.appchat.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
public class FriendResponse {
    @Id
    private int friend_id;
    private String friendNameOfChat;
    private String friendUsername;
    private String friendAvatar;
    private String phone;

    public FriendResponse() {
    }
}
