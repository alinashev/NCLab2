package com.example.lab2.controller.datacontrollers;

import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.concurrent.Future;

public abstract class DataPuller {
    protected URL url;
    protected URLConnection yc;
    protected BufferedReader in;
    protected String inputLine;
    protected StringBuilder sb;

    @Async
    public abstract Future<String> getData(String date);
}
