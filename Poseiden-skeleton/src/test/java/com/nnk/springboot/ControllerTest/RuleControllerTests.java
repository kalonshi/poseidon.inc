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
import com.nnk.springboot.controllers.RuleNameController;

import com.nnk.springboot.repositories.RuleNameRepository;

import com.nnk.springboot.service.RuleNameService;

@RunWith(SpringRunner.class)
@WebMvcTest(RuleNameController.class)
public class RuleControllerTests {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private RuleNameService ruleNameService;

	@MockBean
	RuleNameRepository ruleNameRepository;

	 @WithMockUser(value = "test") 
		@Test
		public void testShowWatchRuleNameList() throws Exception {
	 mvc.perform(get("/ruleName/list")) .andExpect(status().is2xxSuccessful()) ;
	}
	 @WithMockUser(value = "test")	 
		@Test
		public void testAddRuleName() throws Exception {
	 mvc.perform(get("/ruleName/add")) .andExpect(status().is2xxSuccessful()) ;
		}
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveRuleName() throws Exception {
	 * mvc.perform(post("/ruleName/add")) .andExpect(status().is2xxSuccessful()) ; }
	 * 
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testDeleteRuleName() throws Exception {
	 * mvc.perform(get("/ruleName/delete")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 * 
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testUpdateRuleName() throws Exception {
	 * mvc.perform(get("/ruleName/update")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 * 
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveUpdateuRuleName() throws Exception {
	 * mvc.perform(post("/ruleName/update")) .andExpect(status().is2xxSuccessful())
	 * ; }
	 */
}
