package com.nnk.springboot.ControllerTest;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import com.nnk.springboot.service.CurvePointService;

@RunWith(SpringRunner.class)
@WebMvcTest(CurveController.class)
public class CurvePointControllerTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private CurvePointService curvePointService;

	@MockBean
	CurvePointRepository curvePointRepository;
	@MockBean
	CurvePoint curvePoint;
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
	public void testShowWatchCurvePointList() throws Exception {
		mvc.perform(get("/curvePoint/list")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")

	@Test
	public void testAddCurvePoint() throws Exception {
		mvc.perform(get("/curvePoint/add")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")

	@Test
	public void testSaveCurvePoint() throws Exception {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
		String jsonRequest = mapper.writeValueAsString(curvePoint);
		mvc.perform(post("/curvePoint/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@WithMockUser(value = "test")

	@Test
	public void testFailsSaveCurvePoint() throws Exception {
		CurvePoint curvePoint = new CurvePoint();
		String jsonRequest = mapper.writeValueAsString(curvePoint);
		mvc.perform(post("/curvePoint/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testDeleteCurvePoint() throws Exception {
	 * mvc.perform(get("/curvePoint/delete/{id}",41)) .andExpect(status().isOk()) ;
	 * }
	 */

	@WithMockUser(value = "test")
	// Test security of Unregistrer User to update
	@Test
	public void testUpdateCurvePoint() throws Exception {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
		when(curvePointRepository.findById(69)).thenReturn(Optional.of(curvePoint));
		mvc.perform(get("/curvePoint/update/69")).andExpect(status().isOk());
	}
	
	  @WithMockUser(value = "test")
	  
	  @Test public void testSaveUpdateCurvePoint() throws Exception { CurvePoint
	  curvePoint = new CurvePoint(10, 10d, 10d);
	  when(curvePointRepository.findById(69)).thenReturn(Optional.of(curvePoint));
	  curvePoint.setCurveId(2);
	  mvc.perform(post("/curvePoint/update/69")).andExpect(status().isOk()); }
	 
 @WithMockUser(value = "test")
	  
	  @Test public void testFailSaveUpdateCurvePoint() throws Exception { CurvePoint
	  curvePoint = new CurvePoint();
	  when(curvePointRepository.findById(69)).thenReturn(Optional.of(curvePoint));
	  curvePoint.setCurveId(2);
	  mvc.perform(post("/curvePoint/update/69")).andExpect(status().is(200)); }
	 
}
