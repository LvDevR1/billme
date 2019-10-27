package integration

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import com.fasterxml.jackson.databind.ObjectMapper
import com.rest.billme.BillmeApplication
import com.rest.billme.api.beans.RequestLogBean
import com.rest.billme.service.RequestLogService

import configuration.TestConfiguration
import spock.lang.Specification

@SpringBootTest(classes = BillmeApplication.class)
@Import(TestConfiguration)
class CurrencyIntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper mapper

    @Autowired
    RequestLogService requestLogService

    def 'should request currency and log request'() {
        when:

            mockMvc.perform(MockMvcRequestBuilders.get('/currencies/usd')
                .contentType(APPLICATION_JSON_VALUE)
            )                .andExpect(status().isOk())
        then:
            List<RequestLogBean> requestLogs = requestLogService.getAllLogs()
            requestLogs.size() == 1
            requestLogs.get(0).currencyCode == 'usd'
    }

}
