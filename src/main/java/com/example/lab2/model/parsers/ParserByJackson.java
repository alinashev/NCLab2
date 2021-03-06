package com.example.lab2.model.parsers;

import com.example.lab2.interfaces.InjParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@Service
public class ParserByJackson implements InjParser {
    private static ParserByJackson parserByJackson;
    private ObjectMapper mapper = new ObjectMapper();
    private List<Monobank> list;

    private final static Logger logger = Logger.getLogger(ParserByJackson.class);

    public void parseJSON(String json){
        try {
            list = Arrays.asList(mapper.readValue(json, Monobank[].class));
        } catch (JsonProcessingException e) {
            logger.error(e);
        }
    }

    @Override
    public String currentRate(String name) {
        Currency currency = Currency.getInstance(name);
        for(Monobank ob : list){
            if(ob.currencyCodeA.equals(currency.getNumericCodeAsString())){
                return ob.rateSell;
            }
        }
        return "Not exists";
    }

    private static class Monobank{
        private String currencyCodeA;
        private String currencyCodeB;
        private String date;
        private String rateBuy;
        private String rateSell;
        private String rateCross;

        public void setCurrencyCodeA(String currencyCodeA) {
            this.currencyCodeA = currencyCodeA;
        }

        public void setCurrencyCodeB(String currencyCodeB) {
            this.currencyCodeB = currencyCodeB;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setRateBuy(String rateBuy) {
            this.rateBuy = rateBuy;
        }

        public void setRateSell(String rateSell) {
            this.rateSell = rateSell;
        }

        public String getCurrencyCodeA() {
            return currencyCodeA;
        }

        public String getCurrencyCodeB() {
            return currencyCodeB;
        }

        public String getDate() {
            return date;
        }

        public String getRateBuy() {
            return rateBuy;
        }

        public String getRateSell() {
            return rateSell;
        }

        public String getRateCross() {
            return rateCross;
        }

        public void setRateCross(String rateCross) {
            this.rateCross = rateCross;
        }

        @Override
        public String toString() {
            return "Monobank{" +
                    "currencyCodeA='" + currencyCodeA + '\'' +
                    ", currencyCodeB='" + currencyCodeB + '\'' +
                    ", date='" + date + '\'' +
                    ", rateBuy='" + rateBuy + '\'' +
                    ", rateSell='" + rateSell + '\'' +
                    '}';
        }
    }
    public static ParserByJackson parserByJackson(){
        if(parserByJackson == null){
            parserByJackson = new ParserByJackson();
        }
        return parserByJackson;
    }
}
