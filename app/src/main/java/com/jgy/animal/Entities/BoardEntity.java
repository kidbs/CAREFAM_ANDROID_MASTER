package com.jgy.animal.Entities;

public class BoardEntity {
    String title;
    String content;
    String writer;

    String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public BoardEntity(String writer,String title, String content) {
        this.writer=writer;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

//    private String id;
//    private String place1;
//    private String place2;
//    private String title;
//    private String scope;
//    private String comment;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPlace1() {
//        return place1;
//    }
//
//    public void setPlace1(String place1) {
//        this.place1 = place1;
//    }
//
//    public String getPlace2() {
//        return place2;
//    }
//
//    public void setPlace2(String place2) {
//        this.place2 = place2;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getScope() {
//        return scope;
//    }
//
//    public void setScope(String scope) {
//        this.scope = scope;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
