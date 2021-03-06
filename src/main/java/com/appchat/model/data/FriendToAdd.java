package com.appchat.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class FriendToAdd {
    @Id
    @Column(name = "friend_id")
    private int id;
    @Column(name = "friend_nameofchat")
    private String username;
    @Column(name = "friend_avatar")
    private String avatar;

    public FriendToAdd() {
    }
}
