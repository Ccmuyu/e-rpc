package com.zzw;


import java.util.ArrayList;
import java.util.List;

public class TestOOM {
    public static final List<Object> tmp = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(4);

        }
    }
}
