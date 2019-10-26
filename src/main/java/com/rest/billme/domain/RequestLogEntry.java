package com.rest.billme.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="REQUEST_LOG_ENTRIES")
public class RequestLogEntry {

    private @Id
    @GeneratedValue
    Long id;

    private LocalDateTime requestDate;

    private String currencyCode;

    private String clientIp;

    public RequestLogEntry() {
    }
}
