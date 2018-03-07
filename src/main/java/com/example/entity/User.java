package com.example.entity;

import lombok.Data;

/**
 * @author jinx
 * @date 2018/3/7 16:38
 * Desc:
 */
@Data
public class User {
    private String id;
    private String username;
    private String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
