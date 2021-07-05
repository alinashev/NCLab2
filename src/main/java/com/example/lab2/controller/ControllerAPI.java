package com.example.lab2.controller;

import com.example.lab2.interfaces.InjAsyncRunner;
import com.example.lab2.model.converters.ConvertToXML;
import com.example.lab2.model.entities.pojo.CurrencyData;
import com.example.lab2.model.parsers.ParserByJackson;
import com.example.lab2.model.parsers.ParserByOrgJSON;
import com.example.lab2.model.parsers.ParserXml;
import com.example.lab2.model.exception.TimeOutException;
import com.example.lab2.model.pullers.AsyncRunner;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ConvertToXML convert;
    @Autowired
    private CurrencyData currencyData;

    
    private final InjAsyncRunner injAsyncRunner;
    
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private final static Logger logger = Logger.getLogger(ControllerAPI.class);

    public ControllerAPI(InjAsyncRunner injAsyncRunner) {
        this.injAsyncRunner = injAsyncRunner;
    }

    @GetMapping("/api")
    public @ResponseBody Object getData(@RequestParam(value = "currency", defaultValue = "USD") String name,
                                        @RequestParam(value = "type", defaultValue = "JSON") String type,
                                        @RequestParam(value = "date", defaultValue = "") String date,
                                        HttpServletResponse response) {
        type = type.toLowerCase(Locale.ROOT);
        injAsyncRunner.executeAll(date);
        if (type.equals("xml")) {
            try {
                Thread.sleep(1000);
                if (injAsyncRunner.getResultPB() == null || injAsyncRunner.getResultGov() == null) {
                    logger.error("Time out exception. Request time exceeded 1 second.");
                    throw new TimeOutException("Time out exception. Request time exceeded 1 second.");
                } else {
                    ParserXml.parserXml().parseXML(injAsyncRunner.getResultPB());
                    ParserByOrgJSON.parserByOrgJSOn().initializeList(injAsyncRunner.getResultGov());
                    ParserByOrgJSON.parserByOrgJSOn().initListNameCurrency();

                    currencyData.setName(name);
                    currencyData.setCurrencyPBRate(ParserXml.parserXml().currentRate(name));
                    currencyData.setCurrencyGovRate(ParserByOrgJSON.parserByOrgJSOn().currentRate(name));
                    if (date.equals(formatter.format(calendar.getTime())) && injAsyncRunner.getResultMono() != null) {
                        ParserByJackson.parserByJackson().parseJSON(injAsyncRunner.getResultMono());
                        currencyData.setCurrensyMonoRate(ParserByJackson.parserByJackson().currentRate(name));
                    }
                }
            } catch (InterruptedException | TimeOutException e) {
                logger.error(e);
            }
            return convert.convert(currencyData);
        } else if (type.equals("json")) {
            try {
                Thread.sleep(1000);
                if (injAsyncRunner.getResultPB() == null
                        || injAsyncRunner.getResultGov() == null
                        || injAsyncRunner.getResultMono() == null) {
                    logger.error("Time out exception. Request time exceeded 1 second.");
                    throw new TimeOutException("Time out exception. Request time exceeded 1 second.");
                } else {
                    ParserByOrgJSON.parserByOrgJSOn().initializeList(injAsyncRunner.getResultGov());
                    ParserByOrgJSON.parserByOrgJSOn().initListNameCurrency();
                    currencyData.setName(name);
                    currencyData.setCurrencyGovRate(ParserByOrgJSON.parserByOrgJSOn().currentRate(name));
                    currencyData.setCurrencyPBRate(ParserXml.parserXml().currentRate(name));
                    if (date.equals(formatter.format(calendar.getTime()))) {
                        ParserByJackson.parserByJackson().parseJSON(injAsyncRunner.getResultMono());
                        currencyData.setCurrensyMonoRate(ParserByJackson.parserByJackson().currentRate(name));
                    }
                }
            } catch (InterruptedException | TimeOutException e) {
                logger.error(e);
            }
            return currencyData;
        } else {
            try {
                Thread.sleep(1000);
                if (injAsyncRunner.getResultPB() == null
                        || injAsyncRunner.getResultGov() == null
                        || injAsyncRunner.getResultMono() == null) {
                    logger.error("Time out exception. Request time exceeded 1 second.");
                    throw new TimeOutException("Time out exception. Request time exceeded 1 second.");
                } else {
                    ParserByOrgJSON.parserByOrgJSOn().initializeList(injAsyncRunner.getResultGov());
                    ParserByOrgJSON.parserByOrgJSOn().initListNameCurrency();
                    currencyData.setName(name);
                    currencyData.setCurrencyGovRate(ParserByOrgJSON.parserByOrgJSOn().currentRate(name));
                    currencyData.setCurrencyPBRate(ParserXml.parserXml().currentRate(name));
                    if (date.equals(formatter.format(calendar.getTime()))) {
                        ParserByJackson.parserByJackson().parseJSON(injAsyncRunner.getResultMono());
                        currencyData.setCurrensyMonoRate(ParserByJackson.parserByJackson().currentRate(name));
                    }
                }
            } catch (InterruptedException | TimeOutException e) {
                logger.error(e);
            }
            writer.writeData(date, name, currencyData);
            responseWithFile(name, date, response);
        }
        return null;
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
