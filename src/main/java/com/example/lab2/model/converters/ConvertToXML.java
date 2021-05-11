package com.example.lab2.model.converters;

import com.example.lab2.model.entities.pojo.EntityJSON;
import com.example.lab2.model.entities.pojo.EntityXML;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ConvertToXML implements Converter<EntityJSON,EntityXML> {
    @Override
    public EntityXML convert(EntityJSON entityJSON) {
        EntityXML entityXML = new EntityXML();
        entityXML.setName(entityJSON.getName());
        entityXML.setCurrencyGovRate(entityJSON.getCurrencyGovRate());
        entityXML.setCurrencyPBRate(entityJSON.getCurrencyPBRate());
        entityXML.setCurrensyMonoRate(entityJSON.getCurrensyMonoRate());
        return entityXML;
    }
}