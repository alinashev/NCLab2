package com.example.lab2;

import com.example.lab2.controller.datacontrollers.DataPullerGov;
import com.example.lab2.model.entities.ParserJSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


class GetJsonTest {
	private DataPullerGov dataPuller = new DataPullerGov();
	private ParserJSON parserJSON = new ParserJSON();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	@Test
	void getJSON() {
		//parserJSON.initializeList(dataPuller.getData());
		parserJSON.initListNameCurrency();
		Assertions.assertEquals(61, parserJSON.amountOfCurrencies());
		Assertions.assertEquals("10.4129",parserJSON.currentRate("TND"));
		Assertions.assertEquals(simpleDateFormat.format(new Date()), parserJSON.currentData("TND"));
		Assertions.assertEquals("Туніський динар",parserJSON.currentFullName("TND"));
	}

}
