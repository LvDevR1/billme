package com.rest.billme.api;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.billme.api.beans.CurrencyBean;
import com.rest.billme.domain.Currency;
import com.rest.billme.domain.RequestLogEntry;
import com.rest.billme.repository.RequestLogRepository;
import com.rest.billme.service.CurrencyService;

@RestController
@Validated
public class StartController {

    private final CurrencyService currencyService;

    private final RequestLogRepository requestLogRepository;

    StartController(CurrencyService currencyService, RequestLogRepository requestLogRepository) {
        this.currencyService = currencyService;
        this.requestLogRepository = requestLogRepository;
    }

    @GetMapping("/currencies")
    List<Currency> getAllCurrencies() {
        return currencyService.getAll();
    }

    @GetMapping("/logs")
    List<RequestLogEntry> getAllLogs() {
        return requestLogRepository.findAll();
    }

    @GetMapping("/currencies/{currencyCode}")
    CurrencyBean getCurrencyByCode(@PathVariable("currencyCode") @NotBlank @Size(min = 3, max = 3) String currencyCode) {
        return currencyService.getCurrencyByCode(currencyCode);
    }

}
