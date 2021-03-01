package com.example.lab2;

import com.example.lab2.controller.datacontrollers.DataPuller;
import com.example.lab2.controller.datacontrollers.DataPullerPB;
import com.example.lab2.model.entities.ParserXml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GetXmlTest {
	private DataPuller dataPuller = new DataPullerPB();
	private ParserXml parserXml = new ParserXml();
	@Test
	void getXml() {
		//Assertions.assertEquals("AZN", parserXml.shoutXML(dataPuller.getData()));
		//parserXml.parseXML(dataPuller.getData("20210220"));
		Assertions.assertEquals("32.3000000", parserXml.currentRate("CHF"));
		Assertions.assertEquals(7,parserXml.amountOfCurrencies());
	}

}
