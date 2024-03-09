package com.example.da_app_music.Model;

//import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Playlist implements Serializable {

@SerializedName("IdPlaylist")
@Expose
private String idPlaylist;
@SerializedName("Ten")
@Expose
private String ten;
@SerializedName("Hinh")
@Expose
private String hinhPlaylist;
@SerializedName("Icon")
@Expose
private String icon;

public String getIdPlaylist() {
return idPlaylist;
}

public void setIdPlaylist(String idPlaylist) {
this.idPlaylist = idPlaylist;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getHinhPlaylist() {
return hinhPlaylist;
}

public void setHinhPlaylist(String hinh) {
this.hinhPlaylist = hinh;
}

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

}