package com.example.lab2.model.converters;

import com.example.lab2.interfaces.InjDateConverter;
import org.springframework.stereotype.Service;

@Service
public class DateConverter implements InjDateConverter {

    @Override
    public String convertForPb(String date){
        String[] temp = date.split("-");
        return temp[2]+"."+temp[1]+"."+temp[0];
    }
    @Override
    public String convertForMono(String date){
        String[] temp = date.split("-");
        return temp[0];
    }
    @Override
    public String convertForGov(String date){
        String[] temp = date.split("-");
        return temp[0]+temp[1]+temp[2];
    }
}
