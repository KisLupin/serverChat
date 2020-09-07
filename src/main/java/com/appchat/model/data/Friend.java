package com.appchat.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sender_id")
    private int senderId;
    @Column(name = "receive_id")
    private int receiveId;
    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "isaccept")
    private boolean isAccept;

    public Friend() {
    }
}
