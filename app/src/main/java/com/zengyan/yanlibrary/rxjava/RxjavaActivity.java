package com.zengyan.yanlibrary.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zengyan.yanlibrary.R;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class RxjavaActivity extends AppCompatActivity {
     private static final String TAG = RxjavaActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        service.listRepos("ZENGYANYAN");

    }
}
