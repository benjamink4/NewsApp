package com.example.android.newsapp;

public class Article {
    private String title;
    private String WebUrl;
    private String section;
    private String date;
    public Article(String title,String WebUrl,String section,String date){
        this.title=title;
        this.WebUrl=WebUrl;
        this.section=section;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getWebUrl() {
        return WebUrl;
    }

    public String getDate() {
        return date;
    }
}
