package com.example.lab2.model.pullers;

import com.example.lab2.model.converters.DateConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.concurrent.Future;

@Service
public abstract class DataPuller {
    protected URL url;
    protected URLConnection yc;
    protected BufferedReader in;
    protected String inputLine;
    protected StringBuilder sb;
    protected DateConverter dateConverter = new DateConverter();
}
