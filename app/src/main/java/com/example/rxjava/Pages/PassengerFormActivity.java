package com.example.rxjava.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rxjava.Model.Airline;
import com.example.rxjava.Model.PassengerModelRequest;
import com.example.rxjava.Model.PassengerModelResponse;
import com.example.rxjava.databinding.ActivityPassengerFormBinding;
import com.example.rxjava.network.RetrofitClient;
import com.example.rxjava.network.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PassengerFormActivity extends AppCompatActivity {

    private ActivityPassengerFormBinding binding;
    List<Airline> airlineList = new ArrayList<>();

    ServiceInterface serviceInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPassengerFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clickEvent();
        fetchData();
    }

    private void clickEvent() {
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.nameET.getText().toString().isEmpty() && binding.tripET.getText().toString().isEmpty() && binding.airlineET.getText().toString().isEmpty()) {
                    Toast.makeText(PassengerFormActivity.this, "Please enter all field values", Toast.LENGTH_SHORT).show();
                } else {
                    fetchData();
                }
            }
        });
    }

    private void fetchData() {

        serviceInterface = RetrofitClient.getClientPassengerFormActivity(this).create(ServiceInterface.class);

        PassengerModelRequest passengerModelRequest = new PassengerModelRequest();
        passengerModelRequest.setName(binding.nameET.getText().toString());
        passengerModelRequest.setTrips(250);
        passengerModelRequest.setAirline(6);

        compositeDisposable.add(serviceInterface.createPassengerData(passengerModelRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(passengerModelResponse ->  {
                        displayData(passengerModelResponse);
                    }, throwable -> {
                    throwable.getMessage();
                }));
    }

    private void displayData(PassengerModelResponse passengerModelResponse) {
        binding.nameET.setText("");
        binding.tripET.setText("");
        binding.airlineET.setText("");

        /*String airlineId = String.valueOf(passengerModelRequest.getAirline());
        String inputAirlineId = binding.airlineET.getText().toString();*/

        String responseString = passengerModelResponse.getAirline().get(0).getName() + "\n"
                + passengerModelResponse.getAirline().get(0).getCountry();
        binding.responseTV.setText(responseString);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear(); // clear all instance that we created
        super.onStop();
    }
}