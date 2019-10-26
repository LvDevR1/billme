package com.rest.billme.api.beans;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class CurrencyBean {

    private String code;

    private Integer number;

    private String exponent;

    private String name;

}
