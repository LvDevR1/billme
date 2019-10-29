package com.rest.billme.converter

import com.rest.billme.api.beans.CurrencyBean
import com.rest.billme.domain.Currency

import spock.lang.Specification

class CurrencyConverterSpec extends Specification {

    CurrencyConverter converter = new CurrencyConverter()

    def 'should convert currency to bean'() {
        given:
            Currency currency = new Currency(id: 1, code: 'Tst', number: 123, exponent: '1*[2]', name: 'Test currency')
        when:
            CurrencyBean result = converter.convert(currency)
        then:
            result.code == 'Tst'
            result.number == 123
            result.exponent == '1*[2]'
            result.name == 'Test currency'
    }
}
