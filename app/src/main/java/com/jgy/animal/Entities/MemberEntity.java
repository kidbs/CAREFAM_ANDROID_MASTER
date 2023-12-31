package com.jgy.animal.Entities;

public class MemberEntity {
    private String id;
    private String name;
    private String pw;
    private String email;

    public MemberEntity(String id, String name, String email, String pw) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
