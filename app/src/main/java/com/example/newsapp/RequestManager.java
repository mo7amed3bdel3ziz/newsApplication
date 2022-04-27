package com.example.newsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.models.Headline;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(" https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewHeadlines(OnFetchDataListener listener, String category, String query) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<Headline> call = callNewsApi.getHeadlines("eg", context.getString(R.string.api_key));
        try {
            call.enqueue(new Callback<Headline>() {
                @Override
                public void onResponse(Call<Headline> call, Response<Headline> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getArticles() == null) {
                        Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show();
                    } else {
                        listener.onFetchData(response.body().getArticles(), response.message());
                    }

                }

                @Override
                public void onFailure(Call<Headline> call, Throwable t) {
                    listener.onError("Request failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<Headline> getHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);

    }
}
