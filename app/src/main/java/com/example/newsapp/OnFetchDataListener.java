package com.example.newsapp;

import com.example.newsapp.models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener <NewsApiResponse>{
    void onFetchData (List<NewsHeadlines> list , String massage);
    void onError (String massage);
}
