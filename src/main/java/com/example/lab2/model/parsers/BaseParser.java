package com.example.lab2.model.parsers;

import org.springframework.stereotype.Service;

@Service
public abstract class BaseParser {
    public abstract String currentRate(String name);
}
