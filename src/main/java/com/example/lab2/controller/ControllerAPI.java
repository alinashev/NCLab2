package com.example.lab2.controller;

import com.example.lab2.controller.datacontrollers.*;
import com.example.lab2.model.entities.pojo.EntityJSON;
import com.example.lab2.model.parsers.ParserByJackson;
import com.example.lab2.model.parsers.ParserByOrgJSON;
import com.example.lab2.model.entities.pojo.EntityXML;
import com.example.lab2.model.parsers.ParserXml;
import com.example.lab2.model.exception.TimeOutException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


@Controller
public class ControllerAPI {

    private Writer writer = new Writer();
    private AsyncRunner asyncRunner = new AsyncRunner();
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/api")
    public @ResponseBody Object getData(@RequestParam(value = "currency", defaultValue = "USD") String name,
                                        @RequestParam(value = "type", defaultValue = "JSON") String type,
                                        @RequestParam(value = "date", defaultValue = "") String date) {
        type = type.toLowerCase(Locale.ROOT);
        asyncRunner.executeAll(date);
        if(type.equals("xml")){
            EntityXML entityXML = new EntityXML();
            try{
                Thread.sleep(1000);
                if(asyncRunner.getResultPB() == null || asyncRunner.getResultGov() == null){
                    throw new TimeOutException("Time out exception. Request time exceeded 1 second.");
                }else
                {
                    ParserXml.parserXml().parseXML(asyncRunner.getResultPB());
                    ParserByOrgJSON.parserByOrgJSOn().initializeList(asyncRunner.getResultGov());
                    ParserByOrgJSON.parserByOrgJSOn().initListNameCurrency();

                    entityXML.setName(name);
                    entityXML.setCurrencyPBRate(ParserXml.parserXml().currentRate(name));
                    entityXML.setCurrencyGovRate(ParserByOrgJSON.parserByOrgJSOn().currentRate(name));
                    if(date.equals(formatter.format(calendar.getTime())) && asyncRunner.getResultMono()!=null){
                        ParserByJackson.parserByJackson().parseJSON(asyncRunner.getResultMono());
                        entityXML.setCurrensyMonoRate(ParserByJackson.parserByJackson().currentRate(name));
                    }
                }
            }catch (InterruptedException | TimeOutException e){
                System.out.println(e);
            }
            writer.writeData(date,name,entityXML);
            return entityXML;
        }else {
            EntityJSON entityJSON = new EntityJSON();
            try{
                Thread.sleep(1000);
                if(asyncRunner.getResultPB() == null
                        || asyncRunner.getResultGov() == null
                        || asyncRunner.getResultMono() == null){
                    throw new TimeOutException("Time out exception. Request time exceeded 1 second.");
                }else {
                    ParserByOrgJSON.parserByOrgJSOn().initializeList(asyncRunner.getResultGov());
                    ParserByOrgJSON.parserByOrgJSOn().initListNameCurrency();
                    entityJSON.setName(name);
                    entityJSON.setCurrencyGovRate(ParserByOrgJSON.parserByOrgJSOn().currentRate(name));
                    entityJSON.setCurrencyPBRate(ParserXml.parserXml().currentRate(name));
                    if (date.equals(formatter.format(calendar.getTime()))) {
                        ParserByJackson.parserByJackson().parseJSON(asyncRunner.getResultMono());
                        entityJSON.setCurrensyMonoRate(ParserByJackson.parserByJackson().currentRate(name));
                    }
                }
            }catch (InterruptedException | TimeOutException e){
                System.out.println(e);
            }
            writer.writeData(date,name,entityJSON);
            return entityJSON;
        }
    }
}
