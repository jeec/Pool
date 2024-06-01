package com.jerry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StudentBean implements Parcelable {
    public String name;

    public StudentBean(String name) {
        this.name = name;
    }

    protected StudentBean(Parcel in) {
        name = in.readString();
    }

    public static final Creator<StudentBean> CREATOR = new Creator<StudentBean>() {
        @Override
        public StudentBean createFromParcel(Parcel in) {
            return new StudentBean(in);
        }

        @Override
        public StudentBean[] newArray(int size) {
            return new StudentBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
    }
}
