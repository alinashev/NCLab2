package com.example.lab2.controller.datacontrollers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

public class DataPullerGov extends DataPuller{

    @Override
    @Async
    public Future<String> getData(String date) {
        System.out.println("gov");
        sb = new StringBuilder();
        try {
            url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date="
                    + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "&amp;json");
            yc = url.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>(sb.toString());
    }
}
