package com.example.lab2.controller;

import com.example.lab2.model.entities.pojo.EntityJSON;
import com.example.lab2.model.parsers.ParserByJackson;
import com.example.lab2.model.parsers.ParserByOrgJSON;
import com.example.lab2.model.entities.pojo.EntityXML;
import com.example.lab2.model.parsers.ParserXml;
import com.example.lab2.model.exception.TimeOutException;
import com.example.lab2.model.pullers.AsyncRunner;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


@RestController
public class ControllerAPI {

    @Autowired
    private Writer writer;
    @Autowired
    private AsyncRunner asyncRunner;

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private ApplicationContext appContext  = new ClassPathXmlApplicationContext("beans.xml");

    private final static Logger logger = Logger.getLogger(ControllerAPI.class);

    @GetMapping("/api")
    public @ResponseBody void getData(@RequestParam(value = "currency", defaultValue = "USD") String name,
                                        @RequestParam(value = "type", defaultValue = "JSON") String type,
                                        @RequestParam(value = "date", defaultValue = "") String date,
                                      HttpServletResponse response) {
        type = type.toLowerCase(Locale.ROOT);
        asyncRunner.executeAll(date);
        if(type.equals("xml")){
            EntityXML entityXML = (EntityXML) appContext.getBean("xml");
            try{
                Thread.sleep(1000);
                if(asyncRunner.getResultPB() == null || asyncRunner.getResultGov() == null){
                    logger.error("Time out exception. Request time exceeded 1 second.");
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
                logger.error(e);
            }
            writer.writeData(date,name,entityXML);
            responseWithFile(name, date, response);
        }else {
            EntityJSON entityJSON = (EntityJSON) appContext.getBean("json");
            try{
                Thread.sleep(1000);
                if(asyncRunner.getResultPB() == null
                        || asyncRunner.getResultGov() == null
                        || asyncRunner.getResultMono() == null){
                    logger.error("Time out exception. Request time exceeded 1 second.");
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
                logger.error(e);
            }
            writer.writeData(date,name,entityJSON);
            responseWithFile(name, date, response);
        }
    }
    private void responseWithFile(String name, String date, HttpServletResponse response){
        response.setContentType(
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        try {
            InputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(
                    Path.of(date.concat("_").concat(name.toUpperCase()).concat(".docx"))));
            IOUtils.copy(inputStream,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
