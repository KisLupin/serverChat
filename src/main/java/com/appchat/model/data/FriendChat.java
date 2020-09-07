package com.appchat.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class FriendChat {
    @Id
    private int id;
    private String friendNameOfChat;
    private String username;
    private String avatar;
    private String phone;

    public FriendChat() {
    }
}
