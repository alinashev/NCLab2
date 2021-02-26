package com.example.lab2.controller;

import com.example.lab2.controller.datacontrollers.DataPuller;
import com.example.lab2.controller.datacontrollers.DataPullerGov;
import com.example.lab2.controller.datacontrollers.DataPullerMono;
import com.example.lab2.controller.datacontrollers.DataPullerPB;
import com.example.lab2.model.entities.ParserXml;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.concurrent.ExecutionException;


@Controller
public class ControllerAPI {

    private DataPuller dataPullerPB = new DataPullerPB();
    private DataPuller dataPullerMono = new DataPullerMono();
    private DataPuller dataPullerGov = new DataPullerGov();
    @GetMapping("/api")
    public String getData(@RequestParam(value = "currency", defaultValue = "USD") String name,
                          @RequestParam(value = "type", defaultValue = "JSON") String type,
                          @RequestParam(value = "date", defaultValue = "") String date, Model model) {

        type = type.toLowerCase(Locale.ROOT);
        if(type.equals("xml")){
            try {
                ParserXml.parserXml().parseXML(dataPullerPB.getData("date").get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            model.addAttribute("data", ParserXml.parserXml().currentRate(name));
        }
        return "result";
    }
}
