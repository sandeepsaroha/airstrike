package com.airstrike.coinsorter.test;

import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.airstrike.coinsorter.data.CoinsInput;

/**
 * @author Sandeep Saroha
 *
 */
public class CoinsServiceControllerTest extends AbstractAirStrikeApiTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testConisSorter() throws Exception {
		String uri = "/api/coinssorter";
		CoinsInput coinsInput = new CoinsInput();
		coinsInput.setCoins(new Double("1.25"));

		String inputJson = super.mapToJson(coinsInput);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals(content, "{\"dollar\":1,\"halfDollar\":0,\"quarter\":1,\"dime\":0,\"nickel\":0,\"penny\":0}");
	}

}
