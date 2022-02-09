package com.nnk.springboot.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.TradeController;

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

	 @WithMockUser(value = "test") 
		@Test
		public void testShowWatchTradeList() throws Exception {
	 mvc.perform(get("/trade/list")) .andExpect(status().is2xxSuccessful()) ;
	}
	 @WithMockUser(value = "test")	 
		@Test
		public void testAddTrade() throws Exception {
	 mvc.perform(get("/trade/add")) .andExpect(status().is2xxSuccessful()) ;
		}
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveTrade() throws Exception {
	 * mvc.perform(post("/trade/add")) .andExpect(status().is2xxSuccessful()) ; }
	 * 
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testDeleteTrade() throws Exception {
	 * mvc.perform(get("/trade/delete")) .andExpect(status().is2xxSuccessful()) ; }
	 * 
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testUpdateTrade() throws Exception {
	 * mvc.perform(get("/trade/update")) .andExpect(status().is2xxSuccessful()) ; }
	 * 
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveUpdateuTrade() throws Exception {
	 * mvc.perform(post("/trade/update")) .andExpect(status().is2xxSuccessful()) ; }
	 */

}
