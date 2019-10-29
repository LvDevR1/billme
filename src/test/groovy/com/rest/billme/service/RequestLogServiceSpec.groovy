package com.rest.billme.service

import org.springframework.core.convert.ConversionService

import com.rest.billme.api.beans.RequestLogBean
import com.rest.billme.domain.RequestLogEntry
import com.rest.billme.repository.RequestLogRepository

import spock.lang.Specification

class RequestLogServiceSpec extends Specification {

    RequestLogRepository requestLogRepository = Mock(RequestLogRepository)
    ConversionService conversionService = Mock(ConversionService)
    RequestLogService requestLogService = new RequestLogService(requestLogRepository, conversionService)

    def 'should get currency by code'() {
        given:
            RequestLogEntry requestLogEntry = Mock(RequestLogEntry)
            RequestLogBean requestLogBean = new RequestLogBean()
            requestLogEntry.setCurrencyCode('usd')
            conversionService.convert(requestLogEntry, RequestLogBean) >> requestLogBean
        when:
            List<RequestLogBean> result = requestLogService.getAllLogs()
        then:
            1 * requestLogRepository.findAll() >> [requestLogEntry]
            result == [requestLogBean]
    }

}
