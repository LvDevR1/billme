package com.rest.billme.service;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.rest.billme.api.beans.CurrencyBean;
import com.rest.billme.domain.Currency;
import com.rest.billme.repository.CurrencyRepository;

@Component
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final ConversionService conversionService;

    public CurrencyService(CurrencyRepository currencyRepository, ConversionService conversionService) {
        this.currencyRepository = currencyRepository;
        this.conversionService = conversionService;
    }

    public CurrencyBean getCurrencyByCode(String currencyCode) {
        Currency currency = currencyRepository.findByCodeIgnoreCase(currencyCode);
        CurrencyBean currencyBean = new CurrencyBean();

        if (currency != null) {
            currencyBean = conversionService.convert(currency, CurrencyBean.class);
        } else {
            currencyBean.setCode(currencyCode);
            currencyBean.setExponent(".");
            currencyBean.setNumber(999);
            currencyBean.setName("No currency");
        }
        return currencyBean;
    }
}
