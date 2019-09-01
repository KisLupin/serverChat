package com.appchat.model.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sender_id")
    private int sender_id;
    @Column(name = "receive_id")
    private int receive_id;
    @Column(name = "created_time")
    private Date created_time;
    @Column(name = "isaccept")
    private boolean isaccept;

    public Friend() {
    }

    public Friend(int sender_id, int receive_id, Date created_time, boolean isaccept) {
        this.sender_id = sender_id;
        this.receive_id = receive_id;
        this.created_time = created_time;
        this.isaccept = isaccept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public boolean isIsaccept() {
        return isaccept;
    }

    public void setIsaccept(boolean isaccept) {
        this.isaccept = isaccept;
    }
}
