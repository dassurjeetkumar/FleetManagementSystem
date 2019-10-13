
package com.trimax.its.pis.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPISData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPISData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="schedual_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bus_station" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPISData", propOrder = {
    "schedualNumber",
    "busStation"
})
public class GetPISData {

    @XmlElement(name = "schedual_number")
    protected String schedualNumber;
    @XmlElement(name = "bus_station")
    protected String busStation;

    /**
     * Gets the value of the schedualNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchedualNumber() {
        return schedualNumber;
    }

    /**
     * Sets the value of the schedualNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchedualNumber(String value) {
        this.schedualNumber = value;
    }

    /**
     * Gets the value of the busStation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusStation() {
        return busStation;
    }

    /**
     * Sets the value of the busStation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusStation(String value) {
        this.busStation = value;
    }

}
