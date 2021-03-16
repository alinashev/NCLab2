package com.example.lab2.controller.datacontrollers;

import com.example.lab2.model.converters.DateConverter;
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
    protected DateConverter dateConverter = new DateConverter();

    public abstract String getData(String date);
}
