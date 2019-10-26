package com.rest.billme.converter;

import static org.springframework.beans.BeanUtils.copyProperties;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rest.billme.api.beans.CurrencyBean;
import com.rest.billme.domain.Currency;

@Component
public class CurrencyConverter implements Converter<Currency, CurrencyBean> {

    @Override
    public CurrencyBean convert(Currency currency) {
        CurrencyBean currencyBean = new CurrencyBean();
        copyProperties(currency, currencyBean);
        return currencyBean;
    }
}

