package com.rest.billme.interceptors

import static org.springframework.http.HttpStatus.BAD_REQUEST

import javax.validation.ConstraintViolationException

import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.WebRequest

import spock.lang.Specification

class RestExceptionHandlerSpec extends Specification {

    WebRequest webRequest = Mock(WebRequest)

    RestExceptionHandler restExceptionHandler = new RestExceptionHandler()

    def 'should handle constraint validation error'() {
        given:
            ConstraintViolationException exception = Mock(ConstraintViolationException)
            exception.getConstraintViolations() >> []
        when:
            ResponseEntity<Object> result = restExceptionHandler.handleConstraintViolation(exception, webRequest)
        then:
            result != null
            result.getStatusCode() == BAD_REQUEST
    }
}
