package com.rest.billme.converter;

import static org.springframework.beans.BeanUtils.copyProperties;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rest.billme.api.beans.RequestLogBean;
import com.rest.billme.domain.RequestLogEntry;

@Component
public class RequestLogConverter implements Converter<RequestLogEntry, RequestLogBean> {

    @Override
    public RequestLogBean convert(RequestLogEntry requestLogEntry) {
        RequestLogBean requestLogBean = new RequestLogBean();
        copyProperties(requestLogEntry, requestLogBean);
        return requestLogBean;
    }
}

