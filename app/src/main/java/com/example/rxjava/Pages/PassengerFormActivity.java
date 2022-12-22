package com.example.rxjava.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rxjava.Model.Airline;
import com.example.rxjava.Model.PassengerModel;
import com.example.rxjava.Model.PassengerModelRequest;
import com.example.rxjava.databinding.ActivityPassengerFormBinding;
import com.example.rxjava.network.RetrofitClient;
import com.example.rxjava.network.ServiceInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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


        /*PassengerModel passengerModel = new PassengerModel();
        passengerModel.setName(binding.nameET.getText().toString());
        passengerModel.setTrips(5);
        passengerModel.setAirline(airlineList);*/

        PassengerModelRequest passengerModelRequest = new PassengerModelRequest();
        passengerModelRequest.setName(binding.nameET.getText().toString());
        passengerModelRequest.setTrips(250);
        passengerModelRequest.setAirline(5);

        compositeDisposable.add(serviceInterface.createPassengerData(passengerModelRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(passengerModelRequest1 ->  {
                        displayData(passengerModelRequest1);
                    }, throwable -> {
                    throwable.getMessage();
                }));
    }

    private void displayData(PassengerModelRequest passengerModelRequest) {
        binding.nameET.setText("");
        binding.tripET.setText("");
        binding.airlineET.setText("");

        String airlineId = String.valueOf(passengerModelRequest.getAirline());
        String inputAirlineId = binding.airlineET.getText().toString();

        /*if (inputAirlineId == airlineId) {
            String responseString = passengerModels.getAirline().get(0).getName() + "\n" + passengerModels.getAirline().get(0).getCountry() + "\n" + passengerModels.getAirline().get(0).getWebsite();
            binding.responseTV.setText(responseString);
        } else {
            binding.responseTV.setText("No Data");
        }*/
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear(); // clear all instance that we created
        super.onStop();
    }
}