package com.readwrite;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo {

    private static final String url = "http://localhost:8088/api/v1/findByUserId?userId=10000";

    public static  void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i =0 ; i< 50; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    HttpUtils.sendGet(url);
                }
            });
        }

    }
}
