package com.example.da_app_music.Srevice;
//  móc nói cấu hình và dữ liệu trả về

public class APIservice {
    private static String base_url = "http://192.168.31.170:7000/server_mp3/";
    public static DATAsrevice getservice(){

        return APIRetrofitClient.getClient(base_url).create(
                DATAsrevice.class
        );
    }
}
