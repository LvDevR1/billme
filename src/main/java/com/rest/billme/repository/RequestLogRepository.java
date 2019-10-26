package com.rest.billme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.billme.domain.Currency;
import com.rest.billme.domain.RequestLogEntry;

public interface RequestLogRepository extends JpaRepository<RequestLogEntry, Long> {


}