package com.example.lab2.model.entities.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Base {
    protected ObjectMapper mapper = new ObjectMapper();

    protected String name;
    protected String currencyPBRate;
    protected String currensyMonoRate = "Not reachable";
    protected String currencyGovRate;

}
