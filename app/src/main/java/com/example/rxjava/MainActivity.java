package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.rxjava.Model.PostModel;
import com.example.rxjava.databinding.ActivityMainBinding;
import com.example.rxjava.network.ServiceInterface;
import com.example.rxjava.network.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    ServiceInterface serviceInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        serviceInterface = RetrofitClient.getClient(this).create(ServiceInterface.class);
        //api = retrofit.create(Api.class);

        fetchData();
        clickEvent();
    }

    private void clickEvent() {
        binding.continueBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, FormActivity.class));
        });
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

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(postAdapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear(); // clear all instance that we created
        super.onStop();
    }
}