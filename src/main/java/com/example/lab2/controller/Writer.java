package com.example.lab2.controller;

import com.example.lab2.model.WordModel;

public class Writer {
    private WordModel wordModel;
    public void writeData(String date, String name, Object object){
       wordModel = new WordModel(date,name);
       wordModel.generateFile(object);
    }

}
