package com.example.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.models.Article;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectLestener, View.OnClickListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1, b2, b3, b4, b5, b6, b7;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingShow();
        search();
        getArticlesRequest();

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        RequestManager manager = new RequestManager(this);
        manager.getNewHeadlines(listener, "general", null);
    }

    private void search() {
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void loadingShow() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articals");
        dialog.show();
    }

    private void getArticlesRequest() {
        RequestManager manager = new RequestManager(this);
        manager.getNewHeadlines(listener, "general", null);
    }

    private final OnFetchDataListener<Article> listener = new OnFetchDataListener<Article>() {
        @Override
        public void onFetchData(List<Article> list, String massage) {
            if (list.isEmpty()) {
                Toast.makeText(MainActivity.this, "no data found!", Toast.LENGTH_SHORT).show();
            } else {
                ShowNews(list);
            }
            dialog.dismiss();
        }

        @Override
        public void onError(String massage) {
            Toast.makeText(MainActivity.this, "an error occured!!", Toast.LENGTH_SHORT).show();
            //   dialog.dismiss();
        }
    };

    private void ShowNews(List<Article> list) {
        adapter = new CustomAdapter(this, list, this);
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);



        /* recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);*/

    }


    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        switch (view.getId()) {
            case R.id.btn_1:
                setSearchTag(b1.getText().toString());

                break;
            case R.id.btn_2:
                setSearchTag(b2.getText().toString());
                break;
            case R.id.btn_3:
                setSearchTag(b3.getText().toString());
                break;
            case R.id.btn_4:
                setSearchTag(b4.getText().toString());
                break;
            case R.id.btn_5:
                setSearchTag(b5.getText().toString());
                break;
            case R.id.btn_6:
                setSearchTag(b6.getText().toString());
                break;
            case R.id.btn_7:
                setSearchTag(b7.getText().toString());
                break;

        }

    }

    private void setSearchTag(String category) {
        dialog.setTitle("fitching news articles of " + category);
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewHeadlines(listener, category, null);

    }

    @Override
    public void OnNewsClicked(Article headlines) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                .putExtra("data", headlines));

    }
}