package com.appchat.model.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int senderId;
    private int receiverId;
    @CreatedDate
    private Date createdTime;
    private String content;
    private String type;

    public Message() {
    }
}
