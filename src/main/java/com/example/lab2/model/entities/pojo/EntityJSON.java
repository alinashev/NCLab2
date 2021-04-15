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

    @Override
    public int hashCode() {
        int x = 17;
        x = 31 * x + name.hashCode();
        x = 31 * x + currencyPBRate.hashCode();
        x = 31 * x + currensyMonoRate.hashCode();
        x = 31 * x + currencyGovRate.hashCode();
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(!(obj instanceof EntityJSON)) return false;

        EntityJSON entityJSON = (EntityJSON)obj;
        if(this.name == entityJSON.name
                && this.currencyGovRate == entityJSON.currencyGovRate
                && this.currencyPBRate == entityJSON.currencyPBRate
                && this.currensyMonoRate == entityJSON.currensyMonoRate) return true;
        else return false;
    }
}