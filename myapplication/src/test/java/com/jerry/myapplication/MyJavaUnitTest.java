package com.jerry.myapplication;

import org.junit.Test;

public class MyJavaUnitTest extends Object {
    @Test
    public void m1(){
        new Thread(new Myrunnable()).start();
    }
}

class Myrunnable implements Runnable{

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                System.out.println("1111111111");
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
