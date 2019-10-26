package com.rest.billme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.billme.domain.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCodeIgnoreCase(String currencyCode);

}