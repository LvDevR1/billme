package integration

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.ui.ExtendedModelMap

import com.fasterxml.jackson.databind.ObjectMapper
import com.rest.billme.BillmeApplication
import com.rest.billme.api.RequestLogController
import com.rest.billme.api.beans.CurrencyBean
import com.rest.billme.api.beans.ErrorBean
import com.rest.billme.api.beans.RequestLogBean
import com.rest.billme.service.RequestLogService

import configuration.TestConfiguration
import spock.lang.Specification

@SpringBootTest(classes = BillmeApplication.class, webEnvironment = RANDOM_PORT)
@Import(TestConfiguration)
class CurrencyIntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper mapper

    @Autowired
    RequestLogService requestLogService

    @Autowired
    RequestLogController requestLogController

    def 'should request currencies and log request'() {
        when:
            CurrencyBean usdCurrency = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get('/currencies/usd')
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn()
                .response.contentAsString, CurrencyBean)
            CurrencyBean eurCurrency = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get('/currencies/eur')
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString, CurrencyBean)
            CurrencyBean dddCurrency = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get('/currencies/ddd')
                .contentType(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andReturn().response.contentAsString, CurrencyBean)

            ErrorBean errorBean = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get('/currencies/xxxx')
                .contentType(APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest())
                .andReturn().response.contentAsString, ErrorBean)

        then:
            List<RequestLogBean> requestLogs = requestLogService.getAllLogs()
            requestLogs.any({ it -> it.currencyCode == 'usd' })
            requestLogs.any({ it -> it.currencyCode == 'eur' })
            requestLogs.any({ it -> it.currencyCode == 'ddd' })
            requestLogs.any({ it -> it.currencyCode == 'xxxx' })
            usdCurrency.number == 840
            eurCurrency.number == 978
            dddCurrency.number == 999
            errorBean.getMessage() == 'getCurrencyByCode.currencyCode: size must be between 3 and 3'
    }

    def 'should add logs to model'() {
        given:
            ExtendedModelMap model = new ExtendedModelMap()
            mockMvc.perform(MockMvcRequestBuilders.get('/currencies/rub')
                .contentType(APPLICATION_JSON_VALUE)
            )                .andExpect(status().isOk())
        when:
            requestLogController.getAllLogs(model)
        then:
            List<RequestLogBean> result = model.get('requestLogs') as List<RequestLogBean>
            result != null
            result.any({ it -> it.currencyCode == 'rub' })
    }

}
