package com.rest.billme.data;

import static java.lang.Integer.valueOf;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.rest.billme.domain.Currency;
import com.rest.billme.repository.CurrencyRepository;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Component
public class DataInitializer {

    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        Set<Currency> currencies = new HashSet<>();
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/ISO_4217#Active_codes").get();
            Elements tableRows = doc.select(".wikitable").get(0).select("tbody tr");

            tableRows.stream().skip(1).forEach(tr -> {
                    Currency currency = new Currency();
                    currency.setCode(tr.select("td").get(0).text());
                    currency.setNumber(valueOf(tr.select("td").get(1).text()));
                    currency.setExponent(tr.select("td").get(2).text());
                    currency.setName(tr.select("td").get(3).text());
                    currencies.add(currency);
                }
            );

            doc.getAllElements();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currencyRepository.saveAll(currencies);
    }

}
