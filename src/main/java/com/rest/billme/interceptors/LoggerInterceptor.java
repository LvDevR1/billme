package com.rest.billme.interceptors;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.isEmpty;
import static org.springframework.web.servlet.HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rest.billme.domain.RequestLogEntry;
import com.rest.billme.repository.RequestLogRepository;

@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private RequestLogRepository requestLogRepository;

    public LoggerInterceptor(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String requestUri = request.getRequestURI();
        String[] pathParams = requestUri.split("/");
        if (pathParams.length == 3 && "currencies".equals(pathParams[1])) {
            RequestLogEntry entry = new RequestLogEntry();
            String currencyCode = String.valueOf(request.getAttribute(URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            entry.setCurrencyCode(currencyCode);
            entry.setRequestDate(now());
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            ipAddress = isEmpty(ipAddress) ? request.getRemoteAddr() : ipAddress;
            entry.setClientIp(ipAddress);
            requestLogRepository.save(entry);


        }
        return true;
    }

}
