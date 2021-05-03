package com.example.lab2.model;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text.toString());
        try {
            document.write(fos);
            fos.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
