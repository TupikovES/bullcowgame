package com.opencode.test.entity;

import java.util.Date;

public class History {

    private int id;
    private int user_id;
    private int score;
    private Date date;

    public History() {
    }

    public History(int user_id, int score, Date date) {
        this.user_id = user_id;
        this.score = score;
        this.date = date;
    }

    public History(int id, int user_id, int score, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.score = score;
        this.date = date;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
