package com.appchat.model.response;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AddFriendResponse {
    @Id
    private int sender_id;
    private int receive_id;

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }
}
