package com.example.lab2.model.entities.pojo;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

@XmlRootElement(name="currency")
public class EntityXML extends Base {
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getCurrencyPBRate() {
        return currencyPBRate;
    }
    @XmlElement
    public void setCurrencyPBRate(String currencyPBRate) {
        this.currencyPBRate = currencyPBRate;
    }

    public String getCurrensyMonoRate() {
        return currensyMonoRate;
    }
    @XmlElement
    public void setCurrensyMonoRate(String currensyMonoRate) {
        this.currensyMonoRate = currensyMonoRate;
    }

    public String getCurrencyGovRate() {
        return currencyGovRate;
    }
    @XmlElement
    public void setCurrencyGovRate(String currencyGovRate) {
        this.currencyGovRate = currencyGovRate;
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EntityXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(this, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
