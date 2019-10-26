package com.rest.billme.service;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

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

    public CurrencyBean getCurrencyByCode(@PathVariable("currencyCode") @NotBlank @Size(max = 3) String currencyCode) {
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

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

}
