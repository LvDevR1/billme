package com.rest.billme.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "CURRENCIES")
public class Currency {

    private @Id
    @GeneratedValue
    Long id;

    private String code;

    private Integer number;

    private String exponent;

    private String name;

    public Currency() {
    }

}
