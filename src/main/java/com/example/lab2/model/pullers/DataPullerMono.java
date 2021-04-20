package com.example.lab2.model.pullers;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class DataPullerMono extends DataPuller {

    private final static Logger logger = Logger.getLogger(DataPullerMono.class);

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
            logger.error(e);
        }
        return sb.toString();
    }
}
