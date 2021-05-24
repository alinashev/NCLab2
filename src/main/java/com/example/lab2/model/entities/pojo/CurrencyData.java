package com.example.lab2.model.entities.pojo;

import com.example.lab2.model.entities.interfaces.Injection;
import org.springframework.stereotype.Component;

@Component
public class CurrencyData implements Injection {

    private String name;
    private String currencyPBRate = "Not reachable";
    private String currensyMonoRate = "Not reachable";
    private String currencyGovRate = "Not reachable";

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
    public java.lang.String toString() {
        return "Entity{" +
                ", name='" + name + '\'' +
                ", currencyPBRate='" + currencyPBRate + '\'' +
                ", currensyMonoRate='" + currensyMonoRate + '\'' +
                ", currencyGovRate='" + currencyGovRate + '\'' +
                '}';
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
        if(!(obj instanceof CurrencyData)) return false;

        CurrencyData currencyData = (CurrencyData)obj;
        if(this.name == currencyData.name
                && this.currencyGovRate == currencyData.currencyGovRate
                && this.currencyPBRate == currencyData.currencyPBRate
                && this.currensyMonoRate == currencyData.currensyMonoRate) return true;
        else return false;
    }
}