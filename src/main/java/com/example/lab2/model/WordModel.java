package com.example.lab2.model;

import com.example.lab2.model.entities.pojo.CurrencyData;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class WordModel {

    private FileOutputStream fos;
    private XWPFDocument document;

    private final static Logger logger = Logger.getLogger(WordModel.class);

    public WordModel(String date,String name){
        try {
            fos = new FileOutputStream( date + "_" + name + ".docx");
        } catch (FileNotFoundException e) {
            logger.error(e);
        }
    }
    public void generateFile(Object text, String date) throws InvalidFormatException, IOException {
        document = new XWPFDocument(
                OPCPackage.open("src/main/resources/word_template.docx"));
        CurrencyData data = (CurrencyData) text;
        XWPFTable table = document.getTables().get(0);

        XWPFTableRow rowName = table.getRow(0);
        System.out.println(rowName.getCell(0).getText());
        rowName.getCell(1).setText(data.getName());

        XWPFTableRow rowDate = table.getRow(1);
        rowDate.getCell(1).setText(date);

        XWPFTableRow rowPrivat = table.getRow(2);
        rowPrivat.getCell(1).setText(data.getCurrencyPBRate());

        XWPFTableRow rowGov = table.getRow(3);
        rowGov.getCell(1).setText(data.getCurrencyGovRate());

        XWPFTableRow rowMono = table.getRow(4);
        rowMono.getCell(1).setText(data.getCurrensyMonoRate());

        try {
            document.write(fos);
            fos.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
