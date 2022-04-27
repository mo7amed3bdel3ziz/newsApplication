package com.example.newsapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.models.Article;
import com.example.newsapp.models.Headline;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    Article headlines;
    TextView txt_title, txt_time, txt_content, txt_detail, txt_author;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title =findViewById(R.id.text_details_title);
        txt_time =findViewById(R.id.text_details_time);
        txt_content =findViewById(R.id.text_details_content);
        txt_detail=findViewById(R.id.text_details_detail);
        txt_author=findViewById(R.id.text_details_author);
        img_news =findViewById(R.id.img_detail_news);

        headlines = (Article) getIntent().getSerializableExtra("data");

        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_content.setText(headlines.getContent());
        txt_detail.setText(headlines.getDescription());
        Picasso.get().load(headlines.getUrlToImage()).into(img_news);
    }
}