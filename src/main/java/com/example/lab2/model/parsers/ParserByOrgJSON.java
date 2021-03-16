package com.example.lab2.model.parsers;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ParserByOrgJSON extends BaseParser {
    private static ParserByOrgJSON parserByOrgJSON;
    private JSONArray array;
    private List<String> listNameCurrency = new ArrayList<>();

    public void initializeList (String json){
        array = new JSONArray(json);
    }
    public void initListNameCurrency(){
        for (int i = 0; i< array.length(); i++){
            listNameCurrency.add(array.getJSONObject(i).getString("cc"));
        }
    }

    @Override
    public String currentRate(String name){
        if(listNameCurrency.size() > 0)
            return String.valueOf(array.getJSONObject(listNameCurrency.indexOf(name)).getDouble("rate"));
        else return "Not exists";
    }

    public static ParserByOrgJSON parserByOrgJSOn(){
        if(parserByOrgJSON == null){
            parserByOrgJSON = new ParserByOrgJSON();
        }
        return parserByOrgJSON;
    }
}
