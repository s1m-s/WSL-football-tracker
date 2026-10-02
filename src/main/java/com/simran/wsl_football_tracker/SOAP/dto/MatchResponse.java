package com.simran.wsl_football_tracker.SOAP.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchResponse", namespace = "http://wslfootballtracker.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class MatchResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
