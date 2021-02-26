package com.example.lab2.controller.datacontrollers;

import org.springframework.scheduling.annotation.AsyncResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

public class DataPullerPB extends DataPuller{

    @Override
    public Future<String> getData(String date) {
        sb = new StringBuilder();
        try {
            url = new URL("https://api.privatbank.ua/p24api/exchange_rates?xml&date="
                    + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
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
