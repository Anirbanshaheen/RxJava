package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rxjava.Model.DataModal;
import com.example.rxjava.databinding.ActivityFormBinding;
import com.example.rxjava.network.RetrofitClient;
import com.example.rxjava.network.ServiceInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FormActivity extends AppCompatActivity {

    private ActivityFormBinding binding;

    ServiceInterface serviceInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        clickEvent();

        fetchData();
    }

    private void clickEvent() {
        binding.idBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.idEdtName.getText().toString().isEmpty() && binding.idEdtJob.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                } else {
                    fetchData();
                }
            }
        });
    }

    private void fetchData() {
        serviceInterface = RetrofitClient.getClientFormActivity(this).create(ServiceInterface.class);
        DataModal modal = new DataModal(binding.idEdtName.getText().toString(), binding.idEdtJob.getText().toString());

        compositeDisposable.add(serviceInterface.createPost(modal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(postModels -> {
                    displayData(postModels);
                }, throwable -> {
                    throwable.getMessage();
                }));
    }

    private void displayData(DataModal postModels) {
        binding.idEdtName.setText("");
        binding.idEdtJob.setText("");

        String responseString = postModels.getName() + "\n" + postModels.getJob();

        binding.idTVResponse.setText(responseString);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear(); // clear all instance that we created
        super.onStop();
    }
}