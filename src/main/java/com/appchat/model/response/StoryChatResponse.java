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
public class StoryChatResponse {
    @Id
    private int id;
    private int friendId;
    private String friendAvatar;
    private String friendImage;
    private String friendNameOfChat;
    private Date createdTime;

    public StoryChatResponse() {
    }
}
