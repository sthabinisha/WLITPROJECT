package com.example.user.wlitlogin.Model;

import java.io.Serializable;

/**
 * Created by user on 1/28/2017.
 */
public class Items implements Serializable {
public String username;
    public  int image;

    public Items(String username, int image) {
        this.username = username;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
