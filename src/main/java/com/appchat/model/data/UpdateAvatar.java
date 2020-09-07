package com.appchat.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UpdateAvatar {
    @Id
    private int id;
    private String path;

    public UpdateAvatar() {
    }
}
