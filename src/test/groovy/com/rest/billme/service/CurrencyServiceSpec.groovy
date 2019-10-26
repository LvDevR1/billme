package com.rest.billme.service

import org.springframework.core.convert.ConversionService

import com.rest.billme.api.beans.CurrencyBean
import com.rest.billme.domain.Currency
import com.rest.billme.repository.CurrencyRepository

import spock.lang.Specification

class CurrencyServiceSpec extends Specification {

    CurrencyRepository currencyRepository = Mock(CurrencyRepository)

    ConversionService conversionService = Mock(ConversionService)

    CurrencyService currencyService = new CurrencyService(currencyRepository, conversionService)

    def 'should get currency by code'() {
        given:
            Currency currency = Mock(Currency)
            CurrencyBean currencyBean = new CurrencyBean()
            currencyBean.setCode('usd')
            conversionService.convert(currency, CurrencyBean) >> currencyBean
        when:
            CurrencyBean result = currencyService.getCurrencyByCode('usd')
        then:
            1 * currencyRepository.findByCodeIgnoreCase('usd') >> currency
            result == currencyBean
    }

    def 'should return no currency bean'() {
        when:
            CurrencyBean result = currencyService.getCurrencyByCode('ddd')
        then:
            1 * currencyRepository.findByCodeIgnoreCase('ddd') >> null
            result.getName() == 'No currency'

    }

}
