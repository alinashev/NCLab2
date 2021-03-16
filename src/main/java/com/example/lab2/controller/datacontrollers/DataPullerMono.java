package com.example.lab2.controller.datacontrollers;

import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Future;

public class DataPullerMono extends DataPuller {

    @Override
    public String getData(String date) {
        sb = new StringBuilder();
        try {
            url = new URL("https://api.monobank.ua/bank/currency");
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
        return sb.toString();
    }
}
