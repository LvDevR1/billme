package com.rest.billme.converter

import static java.time.LocalDateTime.now

import java.time.LocalDateTime

import com.rest.billme.api.beans.RequestLogBean
import com.rest.billme.domain.RequestLogEntry

import spock.lang.Specification

class RequestLogConverterSpec extends Specification {

    RequestLogConverter converter = new RequestLogConverter()

    def 'should convert currency to bean'() {
        given:
            LocalDateTime requestDate = now()
            RequestLogEntry requestLogEntry = new RequestLogEntry(id: 1, requestDate: requestDate, currencyCode: 'usd', clientIp: '192.123.12.234')
        when:
            RequestLogBean result = converter.convert(requestLogEntry)
        then:
            result.requestDate == requestDate
            result.currencyCode == 'usd'
            result.clientIp == '192.123.12.234'
    }

}
