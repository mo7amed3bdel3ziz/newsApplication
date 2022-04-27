package com.example.newsapp;

import com.example.newsapp.models.Article;
import com.example.newsapp.models.Headline;

import java.util.List;

public interface OnFetchDataListener <NewsApiResponse>{
    void onFetchData (List<Article> list , String massage);
    void onError (String massage);
}
