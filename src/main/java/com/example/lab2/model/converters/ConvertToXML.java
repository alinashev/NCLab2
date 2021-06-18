package com.example.lab2.model.converters;

import com.example.lab2.model.entities.pojo.CurrencyData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

@Service
public class ConvertToXML implements Converter<CurrencyData, String> {
    @Override
    public String convert(CurrencyData currencyData) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(baos);
        xmlEncoder.writeObject(currencyData);
        xmlEncoder.close();
        return baos.toString();
    }
}