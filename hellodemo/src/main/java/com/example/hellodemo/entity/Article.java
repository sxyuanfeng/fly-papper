package com.example.hellodemo.entity;

public class Article {

    private int article_id;
    private int author_id;
    private String content;
    public int getArticleid() {
        return article_id;
    }

    public void setArticleid(int article_id) {
        this.article_id = article_id;
    }

    public int getAuthorid() {
        return author_id;
    }

    public void setAuthorid(int author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    @Override
    public String toString() {
        return "Article [article_id=" + article_id + ", author_id=" + author_id + ", content=" + content + "]";
    }

}
