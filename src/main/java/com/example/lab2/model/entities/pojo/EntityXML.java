package com.example.lab2.model.entities.pojo;

import com.example.lab2.model.entities.interfaces.Injection;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

@Component
@XmlRootElement(name="currency")
public class EntityXML extends Base implements Injection {
    private final static Logger logger = Logger.getLogger(EntityXML.class);

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
            logger.error(e);
        }
        return sw.toString();
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
        if(!(obj instanceof EntityXML)) return false;

        EntityXML entityXML = (EntityXML)obj;
        if(this.name == entityXML.name
                && this.currencyGovRate == entityXML.currencyGovRate
                && this.currencyPBRate == entityXML.currencyPBRate
                && this.currensyMonoRate == entityXML.currensyMonoRate) return true;
        else return false;
    }
}