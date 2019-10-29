package com.rest.billme.api

import com.rest.billme.api.beans.CurrencyBean
import com.rest.billme.service.CurrencyService

import spock.lang.Specification

class CurrencyControllerSpec extends Specification {

    CurrencyService currencyService = Mock(CurrencyService)

    CurrencyController controller = new CurrencyController(currencyService)

    def 'should return currency by code'() {
        given:
            currencyService.getCurrencyByCode('usd') >> new CurrencyBean(code: 'usd')
        when:
            CurrencyBean result = controller.getCurrencyByCode('usd')
        then:
            result.getCode() == 'usd'
    }

}
