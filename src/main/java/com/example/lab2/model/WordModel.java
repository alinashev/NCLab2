package com.example.lab2.model;

import com.example.lab2.model.entities.pojo.CurrencyData;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class WordModel {

    private FileOutputStream fos;
    private XWPFDocument document = new XWPFDocument();

    private final static Logger logger = Logger.getLogger(WordModel.class);


    public WordModel(String date,String name){
        try {
            fos = new FileOutputStream( date + "_" + name + ".docx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void generateFile(Object text){
        CurrencyData data = (CurrencyData) text;
        XWPFTable table = document.createTable();

        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Name");
        tableRowOne.addNewTableCell().setText(data.getName());

        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("Privat");
        tableRowTwo.addNewTableCell().setText(data.getCurrencyPBRate());

        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("Government");
        tableRowThree.addNewTableCell().setText(data.getCurrencyGovRate());

        XWPFTableRow tableRowFour = table.createRow();
        tableRowFour.getCell(0).setText("Monobank");
        tableRowFour.addNewTableCell().setText(data.getCurrensyMonoRate());

        try {
            document.write(fos);
            fos.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
