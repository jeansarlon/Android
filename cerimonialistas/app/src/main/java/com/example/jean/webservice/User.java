package com.example.jean.webservice;

/**
 * Created by jeansarlon on 03/10/16.
 */

public class User {
    private String name;
    private String api_token;
    private Integer id;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, String api_token, Integer id, String email) {
        this.name = name;
        this.api_token = api_token;
        this.id = id;
        this.email = email;
    }
}
