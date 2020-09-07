package com.appchat.model.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
public class UserProfile {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String avatar;
    private String nameOfChat;
    @CreatedDate
    private Date dateCreated;
    private String phone;

    public UserProfile() {
    }
}
