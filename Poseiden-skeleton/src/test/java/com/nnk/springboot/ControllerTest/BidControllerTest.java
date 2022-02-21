package com.nnk.springboot.ControllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)
public class BidControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private BidListService bidListService;

	@MockBean
	BidListRepository bidListRepository;
	@MockBean
	BidList bidList;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	@Before()
	public void setup() {
		// Init MockMvc Object and build
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@WithMockUser(value = "test")
	@Test
	public void testShowWatchBidList() throws Exception {
		mvc.perform(get("/bidList/list")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")
	@Test
	public void testAddBid() throws Exception {
		mvc.perform(get("/bidList/add")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")
	@Test
	public void testSaveBid() throws Exception {
		BidList bid = new BidList("account", "type", 10d);
		String jsonRequest = mapper.writeValueAsString(bid);
		mvc.perform(post("/bidList/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@WithMockUser(value = "test")
	@Test
	public void testFailSaveBid() throws Exception {
		BidList bid = new BidList();
		String jsonRequest = mapper.writeValueAsString(bid);
		mvc.perform(post("/bidList/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}

	@WithMockUser(value = "test")
	@Test
	public void testUpdateBid() throws Exception {
		BidList bid = new BidList("test", "test", 10d);
		when(bidListRepository.findById(69)).thenReturn(Optional.of(bid));
		mvc.perform(get("/bidList/update/69")).andExpect(status().isOk());
	}

	@WithMockUser(value = "test")
	@Test
	public void testSaveUpdateBid() throws Exception {
		BidList bid = new BidList("test", "test", 10d);
		when(bidListRepository.findById(69)).thenReturn(Optional.of(bid));
		bid.setAskQuantity(20d);
		mvc.perform(post("/bidList/update/69")).andExpect(status().isOk());
	}

	@WithMockUser(value = "test")
	@Test
	public void testFailSaveUpdateBid() throws Exception {
		BidList bid = new BidList();
		when(bidListRepository.findById(69)).thenReturn(Optional.of(bid));
		bid.setAskQuantity(20d);
		mvc.perform(post("/bidList/update/69")).andExpect(status().is(200));
	}

	@WithMockUser(value = "test")
	@Test
	public void testDeleteCurvePoint() throws Exception {
		BidList bid = new BidList("test", "test", 10d);
		when(bidListRepository.findById(69)).thenReturn(Optional.of(bid));
		doNothing().when(bidListRepository).delete(bid);
		;
		mvc.perform(get("/bidList/delete/69")).andExpect(redirectedUrl("/bidList/list"));
	}
}