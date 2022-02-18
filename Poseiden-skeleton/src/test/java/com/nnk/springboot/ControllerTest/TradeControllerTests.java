package com.nnk.springboot.ControllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import com.nnk.springboot.service.TradeService;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeController.class)
public class TradeControllerTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private TradeService tradeService;

	@MockBean
	TradeRepository tradeRepository;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	/*
	 * @Before public void setup() { mvc = MockMvcBuilders
	 * .webAppContextSetup(context) .apply(springSecurity()).build(); }
	 */
	@Before()
	public void setup() {
		// Init MockMvc Object and build
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@WithMockUser(value = "test")
	@Test
	public void testShowWatchTradeList() throws Exception {
		mvc.perform(get("/trade/list")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")
	@Test
	public void testAddTrade() throws Exception {
		mvc.perform(get("/trade/add")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")
	@Test
	public void testSaveTrade() throws Exception {

		Trade trade = new Trade("account", "type");

		String jsonRequest = mapper.writeValueAsString(trade);
		mvc.perform(post("/trade/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@WithMockUser(value = "test")
	@Test
	public void testFailSaveTradeEmptyData() throws Exception {

		Trade trade = new Trade();

		String jsonRequest = mapper.writeValueAsString(trade);
		mvc.perform(post("/trade/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testDeleteTrade() throws Exception {
	 * 
	 * Trade trade =new Trade("account"," type");
	 * when(tradeRepository.delete(trade)).thenReturn(Optional.of(trade));
	 * mvc.perform(get("/trade/delete/69")).andExpect(status().isOk()); }
	 */

	@WithMockUser(value = "test")
	@Test
	public void testUpdateTrade() throws Exception {

		Trade trade = new Trade("account", " type");
		when(tradeRepository.findById(69)).thenReturn(Optional.of(trade));
		mvc.perform(get("/trade/update/69")).andExpect(status().isOk());
	}
	
	
	  @WithMockUser(value = "test")
	  
	  @Test public void testWrongDataUpdateTrade() throws Exception {
	  
	  Trade trade = new Trade();
	  when(tradeRepository.findById(69)).thenReturn(Optional.of(trade));
	  mvc.perform(get("/trade/update/69")).andExpect(status().is(200)); }
	  
	 
	  @WithMockUser(value = "test")
	  
	  @Test public void testSaveUpdateTrade() throws Exception {
		  Trade trade = new Trade("account", " type"); 
		  when(tradeRepository.findById(69)).thenReturn(Optional.of(trade));
	  trade.setAccount("accountUpdated");
	  mvc.perform(post("/trade/update/69")).andExpect(status().isOk());
		
	  }
	 

}
