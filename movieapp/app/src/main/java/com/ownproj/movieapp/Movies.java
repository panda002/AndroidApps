package com.ownproj.movieapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ProgressBar;

import java.io.Serializable;
//Sidharth and Nayana
// group Groups1 6
class Movies implements Parcelable {

    String mname;
    String mdesc;
    String genre;
    int seekbarprogress;
    int myear;
    String mimdb;

    @Override
    public String toString() {
        return "Movies{" +
                "mname='" + mname + '\'' +
                ", mdesc='" + mdesc + '\'' +
                ", genre='" + genre + '\'' +
                ", seekbarprogress=" + seekbarprogress +
                ", myear=" + myear +
                ", mimdb='" + mimdb + '\'' +
                '}';
    }

    public Movies(String mname, String mdesc, String genre, Integer seekbarprogress, Integer myear, String mimdb) {
        this.mname = mname;
        this.mdesc = mdesc;
        this.genre = genre;
        this.seekbarprogress = seekbarprogress;
        this.myear = myear;
        this.mimdb = mimdb;

    }

    protected Movies(Parcel in) {
        mname = in.readString();
        mdesc = in.readString();
        genre = in.readString();
        seekbarprogress = in.readInt();
        myear = in.readInt();
        mimdb = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mname);
        parcel.writeString(mdesc);
        parcel.writeString(genre);
        parcel.writeInt(seekbarprogress);
        parcel.writeInt(myear);
        parcel.writeString(mimdb);
    }
}
