package com.example.rxjava.network;

import android.content.Context;

import com.example.rxjava.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //private static Retrofit ourInstance;

    private static Retrofit retrofit = null;
    private static Retrofit retrofitFromActivity = null;
    private static Retrofit retrofitPassengerFromActivity = null;
    private static final long TIMEOUT = 120L;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient(Context context) {
        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")  //BuildConfig.BASE_URL first page https://jsonplaceholder.typicode.com/
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // without call, supporting the service method
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientFormActivity(Context context) {
        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofitFromActivity == null) {
            retrofitFromActivity = new Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")  //BuildConfig.BASE_URL second page https://reqres.in/api/
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // without call, supporting the service method
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitFromActivity;
    }

    public static Retrofit getClientPassengerFormActivity(Context context) {
        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofitPassengerFromActivity == null) {
            retrofitPassengerFromActivity = new Retrofit.Builder()
                    .baseUrl("https://api.instantwebtools.net/v1/")  //BuildConfig.BASE_URL third page https://api.instantwebtools.net/v1/
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // without call, supporting the service method
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitPassengerFromActivity;
    }

    private static void initOkHttp(Context context) {

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        okHttpClient = httpClient.build();
    }

    /*public static Retrofit getInstance() {
        if (ourInstance==null) {
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }

    private RetrofitClient() {

    }*/
}
