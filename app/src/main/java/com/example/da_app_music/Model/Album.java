package com.example.da_app_music.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Album implements Serializable {

@SerializedName("IdAlbum")
@Expose
private String idAlbum;
@SerializedName("TenAlbum")
@Expose
private String tenAlbum;
@SerializedName("TenCaSi")
@Expose
private String tenCaSi;
@SerializedName("HinhAlbum")
@Expose
private String hinhAlbum;

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getTenAlbum() {
return tenAlbum;
}

public void setTenAlbum(String tenAlbum) {
this.tenAlbum = tenAlbum;
}

public String getTenCaSi() {
return tenCaSi;
}

public void setTenCaSi(String tenCaSi) {
this.tenCaSi = tenCaSi;
}

public String getHinhAlbum() {
return hinhAlbum;
}

public void setHinhAlbum(String hinhAlbum) {
this.hinhAlbum = hinhAlbum;
}

}