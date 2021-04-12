package com.example.lab2.model.entities.pojo;

import com.example.lab2.model.entities.interfaces.Injection;
import com.fasterxml.jackson.core.JsonProcessingException;

public class EntityJSON extends Base implements Injection {

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getCurrencyPBRate() {
        return currencyPBRate;
    }
    public void setCurrencyPBRate(String currencyPBRate) {
        this.currencyPBRate = currencyPBRate;
    }

    public String getCurrensyMonoRate() {
        return currensyMonoRate;
    }
    public void setCurrensyMonoRate(String currensyMonoRate) {
        this.currensyMonoRate = currensyMonoRate;
    }

    public String getCurrencyGovRate() {
        return currencyGovRate;
    }
    public void setCurrencyGovRate(String currencyGovRate) {
        this.currencyGovRate = currencyGovRate;
    }

    @Override
    public String toString() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}