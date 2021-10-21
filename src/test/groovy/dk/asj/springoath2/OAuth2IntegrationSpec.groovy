package dk.asj.springoath2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class OAuth2IntegrationSpec extends Specification{

  @Autowired
  MockMvc client

  def static baseUrl = 'http://loclahost:8080'

  def 'should allow all access to /'() {
    when:
    def response = client.perform(get(baseUrl))

    then:
    response.andExpect(status().isOk())
    response.andExpect(content().string('hello from unrestricted endpoint'))
  }

  def 'should reject unauthenticated access to secured endpoint'() {
    given:
    def requestUrl = "${baseUrl}/secret"

    when:
    def response = client.perform(get(requestUrl))

    then:
    response.andExpect(status().isForbidden())
  }
}
