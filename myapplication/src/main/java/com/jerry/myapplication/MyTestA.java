package com.jerry.myapplication;

import android.util.Log;

public class MyTestA {

    public void doSth() {
        synchronized(MyTestA.class){
            Log.i(">>>", "asdf");
        }
    }
}


interface abc{
    void m1();
}