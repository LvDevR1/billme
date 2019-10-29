package com.rest.billme.api

import org.springframework.ui.ExtendedModelMap

import com.rest.billme.api.beans.RequestLogBean
import com.rest.billme.service.RequestLogService

import spock.lang.Specification

class RequestLogControllerSpec extends Specification {

    RequestLogService requestLogService = Mock(RequestLogService)

    RequestLogController controller = new RequestLogController(requestLogService)

    def 'should return currency by code'() {
        given:
            ExtendedModelMap model = new ExtendedModelMap()
            RequestLogBean bean = new RequestLogBean()
            requestLogService.getAllLogs() >> [bean]
        when:
            String result = controller.getAllLogs(model)
        then:

            model.get('requestLogs') == [bean]
            result == 'index'
    }

}
