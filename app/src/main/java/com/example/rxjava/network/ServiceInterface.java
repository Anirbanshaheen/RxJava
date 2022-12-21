package com.example.rxjava.network;



import com.example.rxjava.Model.DataModal;
import com.example.rxjava.Model.PostModel;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceInterface {

    @GET("posts")
    Single<List<PostModel>> getPosts();

    @POST("users")
    Single<DataModal> createPost(@Body DataModal dataModal);
 }
