package com.rest.billme.interceptors

import javax.servlet.http.HttpServletRequest

import com.rest.billme.repository.RequestLogRepository

import spock.lang.Specification

class RequestLoggerInterceptorSpec extends Specification {

    RequestLogRepository requestLogRepository = Mock(RequestLogRepository)

    RequestLoggerInterceptor loggerInterceptor = new RequestLoggerInterceptor(requestLogRepository)

    def "should intercept and save request log"() {
        given:
            HttpServletRequest request = Mock(HttpServletRequest)
            request.getRequestURI() >> '/currencies/usd'
        when:
            loggerInterceptor.preHandle(request, null, null)

        then:
            1 * requestLogRepository.save({ it.currencyCode == 'usd' })
    }

    def "should intercept and not save request log"() {
        given:
            HttpServletRequest request = Mock(HttpServletRequest)
            request.getRequestURI() >> '/current/usd'
        when:
            loggerInterceptor.preHandle(request, null, null)

        then:
            0 * requestLogRepository.save(_)
    }


}
