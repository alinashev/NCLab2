package com.example.lab2.model;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;

public class WordModel {
    FileOutputStream fos;
    XWPFDocument document = new XWPFDocument();

    public WordModel(String date,String name){
        try {
            fos = new FileOutputStream(new File( date + "_" + name + ".docx"));
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
            e.printStackTrace();
        }
    }
}
