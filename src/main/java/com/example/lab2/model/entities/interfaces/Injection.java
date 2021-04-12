package com.example.lab2.model.entities.interfaces;

public interface Injection {
    void setName(String name);
    void setCurrencyPBRate(String currencyPBRate);
    void setCurrensyMonoRate(String currensyMonoRate);
    void setCurrencyGovRate(String currencyGovRate);

    String getName();
    String getCurrencyPBRate();
    String getCurrensyMonoRate();
    String getCurrencyGovRate();
}
