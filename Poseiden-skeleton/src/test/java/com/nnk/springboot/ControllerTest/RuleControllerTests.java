package com.nnk.springboot.ControllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
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

	@MockBean
	RuleName ruleName;

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
	public void testShowWatchRuleNameList() throws Exception {
		mvc.perform(get("/ruleName/list")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")
	@Test
	public void testAddRuleName() throws Exception {
		mvc.perform(get("/ruleName/add")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")
@Test
	public void testSaveRuleName() throws Exception {

		RuleName ruleName = new RuleName("name", " description", " json", "template", " sqlStr", " sqlPart");

		String jsonRequest = mapper.writeValueAsString(ruleName);
		mvc.perform(post("/ruleName/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	@WithMockUser(value = "test")
	@Test
		public void testFailSaveRuleName() throws Exception {

			RuleName ruleName = new RuleName("name", " description", " json", "template", " sqlStr", " sqlPart");

			String jsonRequest = mapper.writeValueAsString(ruleName);
			mvc.perform(post("/ruleName/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200));

		}
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testDeleteRuleName() throws Exception {
	 * mvc.perform(get("/ruleName/delete")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 */
	@WithMockUser(value = "test")

	@Test
	public void testUpdateRuleName() throws Exception {
		RuleName ruleName = new RuleName("name", " description", " json", "template", " sqlStr", " sqlPart");

		when(ruleNameRepository.findById(69)).thenReturn(Optional.of(ruleName));
		mvc.perform(get("/ruleName/update/69")).andExpect(status().isOk());
	}

	@WithMockUser(value = "test")

	@Test
	public void testSaveUpdateRuleName() throws Exception {
		RuleName ruleName = new RuleName("name", " description", " json", "template", " sqlStr", " sqlPart");
		when(ruleNameRepository.findById(64)).thenReturn(Optional.of(ruleName));
		ruleName.setName("descriptionUpdated");
		mvc.perform(post("/ruleName/update/64")).andExpect(status().isOk());
	}
	@WithMockUser(value = "test")

	@Test
	public void testFailSaveUpdateRuleName() throws Exception {
		RuleName ruleName = new RuleName();
		when(ruleNameRepository.findById(64)).thenReturn(Optional.of(ruleName));
		ruleName.setName("descriptionUpdated");
		mvc.perform(post("/ruleName/update/64")).andExpect(status().is(200));
	}
}
