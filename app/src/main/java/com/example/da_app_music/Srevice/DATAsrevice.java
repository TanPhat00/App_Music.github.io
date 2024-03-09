package com.example.da_app_music.Srevice;

import com.example.da_app_music.Model.Album;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.Model.ChuDe;
import com.example.da_app_music.Model.Playlist;
import com.example.da_app_music.Model.Quangcao;
import com.example.da_app_music.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

// gửi phương thức và những dữ liệu từ server về
public interface DATAsrevice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrent.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chude.php")
    Call<List<ChuDe>> GetDataChuDe();

    @GET("theloai.php")
    Call<List<TheLoai>> GetDataTheLoai();
    @GET("albumhost.php")
    Call<List<Album>> GetDataAlbum();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetDataBaiHatDuocThich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachtheotheloai(@Field("idtheloai") String idtheloai);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachcacplaylist();

    @GET("tatcacacchude.php")
    Call<List<ChuDe>> Getdatatatcacchude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> Gettheloaitheochude(@Field("idchude") String idchude);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")

    Call<List<BaiHat>> Getdanhsachbaihattheoalbum(@Field("idalbum") String idalbum);
    @GET("tatcaallbum.php")
    Call<List<Album>> Gettatcaalbum();

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<BaiHat>> GetDataSearchBaiHat(@Field("tukhoa") String tukhoa);

}
