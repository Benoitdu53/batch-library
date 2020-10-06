package com.web.library.batchlibrary.model;

public class LoginBean {

    private String username = "admin@admin.fr";
    private String password = "admin";

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
