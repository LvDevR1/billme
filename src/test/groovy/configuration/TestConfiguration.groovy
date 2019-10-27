package configuration

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

class TestConfiguration {

    @Bean
    MockMvc testingMockMvc(WebApplicationContext webApplicationContext) {
        webAppContextSetup(webApplicationContext).build();
    }

}
