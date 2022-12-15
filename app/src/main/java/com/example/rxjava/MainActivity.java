package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rxjava.Model.PostModel;
import com.example.rxjava.network.ServiceInterface;
import com.example.rxjava.network.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ServiceInterface serviceInterface;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceInterface = RetrofitClient.getClient(this).create(ServiceInterface.class);
        //api = retrofit.create(Api.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add(serviceInterface.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) // todo subscribe with when use
                .subscribe(postModels -> {
                    displayData(postModels);
                }, throwable -> {
                    throwable.getMessage();
                }));
    }

    private void displayData(List<PostModel> postModels) {
        PostAdapter postAdapter = new PostAdapter(this, postModels);
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear(); // clear all instance that we created
        super.onStop();
    }
}