package com.example.da_app_music.Model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class BaiHat implements Parcelable {

@SerializedName("Idbaihat")
@Expose
private String Idbaihat;
@SerializedName("Idalbum")
@Expose
private String Idalbum;
@SerializedName("Idtheloai")
@Expose
private String Idtheloai;
@SerializedName("Idplaylist")
@Expose
private String Idplaylist;
@SerializedName("Tenbaihat")
@Expose
private String Tenbaihat;
@SerializedName("Hinhbaihat")
@Expose
private String Hinhbaihat;
@SerializedName("Casi")
@Expose
private String Casi;
@SerializedName("Linkbaihat")
@Expose
private String Linkbaihat;
@SerializedName("Luotthich")
@Expose
private String Luotthich;

public String getIdbaihat() {
return Idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.Idbaihat = idbaihat;
}

public String getIdalbum() {
return Idalbum;
}

public void setIdalbum(String idalbum) {
this.Idalbum = idalbum;
}

public String getIdtheloai() {
return Idtheloai;
}

public void setIdtheloai(String idtheloai) {
this.Idtheloai = idtheloai;
}

public String getIdplaylist() {
return Idplaylist;
}

public void setIdplaylist(String idplaylist) {
this.Idplaylist = idplaylist;
}

public String getTenbaihat() {
return Tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.Tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return Hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.Hinhbaihat = hinhbaihat;
}

public String getCasi() {
return Casi;
}

public void setCasi(String casi) {
this.Casi = casi;
}

public String getLinkbaihat() {
return Linkbaihat;
}

public void setLinkbaihat(String linkbaihat) {
this.Linkbaihat = linkbaihat;
}

public String getLuotthich() {
return Luotthich;
}

public void setLuotthich(String luotthich) {
this.Luotthich = luotthich;
}
    protected BaiHat(Parcel in) {
        Idbaihat = in.readString();
        Idalbum = in.readString();
        Idtheloai = in.readString();
        Idplaylist = in.readString();
        Tenbaihat = in.readString();
        Hinhbaihat = in.readString();
        Casi = in.readString();
        Linkbaihat = in.readString();
        Luotthich = in.readString();
    }
    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Idbaihat);
        dest.writeString(Idalbum);
        dest.writeString(Idtheloai);
        dest.writeString(Idplaylist);
        dest.writeString(Tenbaihat);
        dest.writeString(Hinhbaihat);
        dest.writeString(Casi);
        dest.writeString(Linkbaihat);
        dest.writeString(Luotthich);
    }
}