package com.appchat.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MessageChatResponse {
    @Id
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private String type;
}
