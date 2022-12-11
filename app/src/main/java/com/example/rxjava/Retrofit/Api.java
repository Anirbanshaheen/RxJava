package com.example.rxjava.Retrofit;



import com.example.rxjava.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("posts")
    Observable<List<Post>> getPosts();
 }
