package com.example.da_app_music.Srevice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIRetrofitClient {
    // Sử lý việc lòa dữ liệu từ server về nếu server không trả dữ liệu về quá thời gian cho phép thì thực thi việc ngắt kết nối
   //
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000000, TimeUnit.MICROSECONDS)
                .writeTimeout(10000000,TimeUnit.MICROSECONDS)
                .connectTimeout(10000000,TimeUnit.MICROSECONDS)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        // Sử dụng thư viện Gson để đọc dữ liệu trả về từ server
        // 1 cấu hình đọc đường dẫn
        // 2 cấu hình phương thức tương tác của phần mạng
        // 3 cấu hình chuyển đổi dữ liệu api thành biến của java
        
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(base_url);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        retrofit = builder
                .build();
        return retrofit;

    }

}
