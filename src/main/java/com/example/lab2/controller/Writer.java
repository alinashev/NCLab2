package com.example.lab2.controller;

import com.example.lab2.model.WordModel;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class Writer {
    private WordModel wordModel;
    private final static Logger logger = Logger.getLogger(WordModel.class);

    public void writeData(String date, String name, Object object){
       wordModel = new WordModel(date,name);
        try {
            wordModel.generateFile(object, date);
        } catch (InvalidFormatException | IOException e) {
            logger.error(e);
        }
    }

}
