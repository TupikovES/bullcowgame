package com.opencode.test.entity;

public class Rating {

    private int id;
    private int user_id;
    private String userName;
    private float rating;

    public Rating() {
    }

    public Rating(int user_id, float rating) {
        this.user_id = user_id;
        this.rating = rating;
    }

    public Rating(int id, int user_id, float rating) {
        this.id = id;
        this.user_id = user_id;
        this.rating = rating;
    }

    public Rating(int id, int user_id, String userName, float rating) {
        this.id = id;
        this.user_id = user_id;
        this.userName = userName;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
