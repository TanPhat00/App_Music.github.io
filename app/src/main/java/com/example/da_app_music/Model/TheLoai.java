package com.example.da_app_music.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class TheLoai implements Serializable {

@SerializedName("IdTheLoai")
@Expose
private String idTheLoai;
@SerializedName("IdKeyChuDe")
@Expose
private String idKeyChuDe;
@SerializedName("TenTheLoai")
@Expose
private String tenTheLoai;
@SerializedName("HinhTheloai")
@Expose
private String hinhTheloai;

public String getIdTheLoai() {
return idTheLoai;
}

public void setIdTheLoai(String idTheLoai) {
this.idTheLoai = idTheLoai;
}

public String getIdKeyChuDe() {
return idKeyChuDe;
}

public void setIdKeyChuDe(String idKeyChuDe) {
this.idKeyChuDe = idKeyChuDe;
}

public String getTenTheLoai() {
return tenTheLoai;
}

public void setTenTheLoai(String tenTheLoai) {
this.tenTheLoai = tenTheLoai;
}

public String getHinhTheloai() {
return hinhTheloai;
}

public void setHinhTheloai(String hinhTheloai) {
this.hinhTheloai = hinhTheloai;
}

}