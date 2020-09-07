package com.appchat.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AddFriendResponse {
    @Id
    private int sender_id;
    private int receive_id;

    public AddFriendResponse() {
    }
}
