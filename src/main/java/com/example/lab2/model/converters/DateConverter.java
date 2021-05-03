package com.example.lab2.model.converters;

import org.springframework.stereotype.Service;

@Service
public class DateConverter {

    public String convertForPb(String date){
        String[] temp = date.split("-");
        return temp[2]+"."+temp[1]+"."+temp[0];
    }
    public String convertForMono(String date){
        String[] temp = date.split("-");
        return temp[0];
    }
    public String convertForGov(String date){
        String[] temp = date.split("-");
        return temp[0]+temp[1]+temp[2];
    }
}
