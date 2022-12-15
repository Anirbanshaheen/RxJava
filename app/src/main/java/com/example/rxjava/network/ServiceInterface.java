package com.example.rxjava.network;



import com.example.rxjava.Model.PostModel;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ServiceInterface {

    @GET("posts")
    Single<List<PostModel>> getPosts();
 }
