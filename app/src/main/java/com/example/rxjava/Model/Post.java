package com.example.rxjava.Model;

public class Post {
    public int userId;
    public int it;
    public String title;
    public String body;

    public Post() {
    }

    public Post(int userId, int it, String title, String body) {
        this.userId = userId;
        this.it = it;
        this.title = title;
        this.body = body;
    }
}
