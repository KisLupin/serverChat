package com.appchat.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "add_friend_request")
@Getter
@Setter
public class AddFriendRequest {
    @Id
    @Column(name = "sender_id")
    private int sender_id;
    @Column(name = "receive_id")
    private int receiver_id;
    @Column(name = "send_addfriend")
    private int is_send;

    public AddFriendRequest() {
    }
}
