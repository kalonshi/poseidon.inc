package com.nnk.springboot.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.CurveController;

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

	
	 @WithMockUser(value = "test") 
	@Test
	public void testShowWatchCurvePointList() throws Exception {
 mvc.perform(get("/curvePoint/list")) .andExpect(status().is2xxSuccessful()) ;
}
	 
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testAddCurvePoint() throws Exception {
	 * mvc.perform(get("/curvePoint/add")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 */
	
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveCurvePoint() throws Exception {
	 * mvc.perform(post("/curvePoint/add")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 */
	
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testDeleteCurvePoint() throws Exception {
	 * mvc.perform(get("/curvePoint/delete/{id}",(Integer)41))
	 * .andExpect(status().isOk()) ; }
	 */
	 
	  
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testUpdateCurvePoint() throws Exception {
	 * mvc.perform(get("/curvePoint/update/{id}",41)) .andExpect(status().isOk()) ;
	 * }
	 */
	 
	  
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveUpdateCurvePoint() throws Exception {
	 * mvc.perform(post("/curvePoint/update"))
	 * .andExpect(status().is2xxSuccessful()) ; }
	 */
	 
	 
	 
}
