package com.rest.billme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.rest.billme.api.beans.RequestLogBean;
import com.rest.billme.repository.RequestLogRepository;

@Component
public class RequestLogService {

    private final RequestLogRepository requestLogRepository;

    private final ConversionService conversionService;

    public RequestLogService(RequestLogRepository requestLogRepository, ConversionService conversionService) {
        this.requestLogRepository = requestLogRepository;
        this.conversionService = conversionService;
    }

    public List<RequestLogBean> getAllLogs() {
        return requestLogRepository.findAll().stream()
            .map(it -> conversionService.convert(it, RequestLogBean.class))
            .collect(Collectors.toList());
    }

}
