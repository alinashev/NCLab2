package com.example.lab2.model.converters;

import com.example.lab2.model.entities.pojo.EntityJSON;
import com.example.lab2.model.entities.pojo.EntityXML;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ConvertToJSON implements Converter<EntityXML, EntityJSON> {
    @Override
    public EntityJSON convert(EntityXML entityXML) {
        EntityJSON entityJSON = new EntityJSON();
        entityJSON.setName(entityXML.getName());
        entityJSON.setCurrencyGovRate(entityXML.getCurrencyGovRate());
        entityJSON.setCurrencyPBRate(entityXML.getCurrencyPBRate());
        entityJSON.setCurrensyMonoRate(entityXML.getCurrensyMonoRate());
        return entityJSON;
    }
}