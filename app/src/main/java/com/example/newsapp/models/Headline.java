package com.example.newsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Headline implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private float totalResults;
    @SerializedName("articles")
    @Expose
    private ArrayList<Article> articles;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(float totalResults) {
        this.totalResults = totalResults;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public float getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}