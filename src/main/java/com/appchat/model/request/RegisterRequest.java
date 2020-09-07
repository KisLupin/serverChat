package com.appchat.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RegisterRequest {
    @Id
    private int id;
    private String username;
    private String password;
    private String nameOfChat;

    public RegisterRequest() {
    }
}
