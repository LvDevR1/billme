package com.rest.billme.api.beans;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RequestLogBean {

    private LocalDateTime requestDate;

    private String currencyCode;

    private String clientIp;

}
