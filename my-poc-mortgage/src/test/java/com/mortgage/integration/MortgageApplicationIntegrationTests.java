package com.mortgage.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mortgage.MortgageApplication;
import com.mortgage.model.Amount;
import com.mortgage.model.Currency;
import com.mortgage.model.Mortgage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		MortgageApplication.class })
@ActiveProfiles("test")
public class MortgageApplicationIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private MockMvc mockMvc;

	@Before
	public void setUpMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetInterestRates() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/api/interest-rates")).andExpect(status().isOk()).andReturn();

		assertThat("application/json;charset=UTF-8", is(mvcResult.getResponse().getContentType()));

	}

	@Test
	public void testMortgageCheck() throws Exception {

		Mortgage mort = new Mortgage();
		mort.setHomeValue(new Amount(BigDecimal.valueOf(300000), Currency.EUR));
		mort.setIncome(new Amount(BigDecimal.valueOf(10000), Currency.EUR));
		mort.setLoanValue(new Amount(BigDecimal.valueOf(200000), Currency.EUR));
		mort.setFixedInterestRatePeriod(15);

		HttpEntity<Mortgage> entity = new HttpEntity<>(mort, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/mortgage-check"),
				HttpMethod.POST, entity, String.class);
		String expected = "{\"id\":null,\"income\":{\"currency\":\"EUR\",\"value\":10000},\"maturityPeriod\":15,\"loanValue\":{\"currency\":\"EUR\",\"value\":200000},\"homeValue\":{\"currency\":\"EUR\",\"value\":300000},\"isFeasible\":true,\"monthlyPayment\":{\"currency\":\"EUR\",\"value\":1484.89}}";
		String actual = response.getBody();
		System.out.println(actual);

		assertThat(actual, is(expected));

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:8080" + uri;
	}

}
