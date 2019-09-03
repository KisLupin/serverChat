package com.appchat.model.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "add_friend_request")
public class AddFriendRequest {
    @Id
    @Column(name = "sender_id")
    private int sender_id;
    @Column(name = "receive_id")
    private int receiver_id;
    @Column(name = "send_addfriend")
    private int is_send;

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getIs_send() {
        return is_send;
    }

    public void setIs_send(int is_send) {
        this.is_send = is_send;
    }
}
