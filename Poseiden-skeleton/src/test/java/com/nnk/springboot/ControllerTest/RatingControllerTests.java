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

import com.nnk.springboot.controllers.RatingController;

import com.nnk.springboot.repositories.RatingRepository;

import com.nnk.springboot.service.RatingSercive;

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
public class RatingControllerTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private RatingSercive ratingService;

	@MockBean
	RatingRepository ratingRepository;
	
	 @WithMockUser(value = "test") 
		@Test
		public void testShowWatchRatingList() throws Exception {
	 mvc.perform(get("/rating/list")) .andExpect(status().is2xxSuccessful()) ;
	}
	 
	 @WithMockUser(value = "test")	 
		@Test
		public void testAddrating() throws Exception {
	 mvc.perform(get("/rating/add")) .andExpect(status().is2xxSuccessful()) ;
		}
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveRating() throws Exception {
	 * mvc.perform(post("/rating/add")) .andExpect(status().is2xxSuccessful()) ; }
	 */
		
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * 
	 * @Test public void testDeleteRating() throws Exception {
	 * mvc.perform(get("/rating/delete")) .andExpect(status().is2xxSuccessful()) ; }
	 */
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testUpdateRating() throws Exception {
	 * mvc.perform(get("/rating/update/{id}",47)).andExpect(status().isOk()) ; }
	 */ 
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveUpdateRating() throws Exception {
	 * mvc.perform(post("/rating/update")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 */
	 
	
}
