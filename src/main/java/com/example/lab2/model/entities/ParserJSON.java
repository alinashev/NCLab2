package com.example.lab2.model.entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserJSON {
    private static ParserJSON parserJSON;
    private JSONArray array;
    private List<String> listNameCurrency = new ArrayList<>();

    public void initializeList (String json){
        array = new JSONArray(json);
    }
    public String getValue(){
        return array.getJSONObject(0).getString("cc");
    }
    public void initListNameCurrency(){
        for (int i = 0; i< array.length(); i++){
            listNameCurrency.add(array.getJSONObject(i).getString("cc"));
        }
    }

    public int amountOfCurrencies(){
        return listNameCurrency.size();
    }

    public String currentRate(String name){
        if(listNameCurrency.size() > 0) return array.getJSONObject(listNameCurrency.indexOf(name)).getString("rate");
        else return "Not exists";
    }

    public String currentData(String name){
        if(listNameCurrency.size() > 0) return array.getJSONObject(listNameCurrency.indexOf(name)).getString("exchangedate");
        else return "Not exists";
    }

    public String currentFullName(String name){
        if(listNameCurrency.size() > 0) return array.getJSONObject(listNameCurrency.indexOf(name)).getString("txt");
        else return "Not exists";
    }

    public String buildJSONString(String name){
        JSONObject jsonObject = new JSONObject();
        jsonObject.
                append("name",name).
                append("rate",currentRate(name));
        return jsonObject.toString();
    }

    public static ParserJSON parserJSON(){
        if(parserJSON == null){
            parserJSON = new ParserJSON();
        }
        return parserJSON;
    }
}
