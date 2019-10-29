package com.rest.billme.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.billme.api.beans.CurrencyBean;
import com.rest.billme.service.CurrencyService;

@RestController
@Validated
public class CurrencyController {

    private final CurrencyService currencyService;

    CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/currencies/{currencyCode}", produces = APPLICATION_JSON_VALUE)
    CurrencyBean getCurrencyByCode(@PathVariable("currencyCode") @NotBlank @Size(min = 3, max = 3) String currencyCode) {
        return currencyService.getCurrencyByCode(currencyCode);
    }

}
