package com.nnk.springboot.ControllerTest;

import static org.mockito.Mockito.doNothing;
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
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
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

	@Autowired
	private WebApplicationContext context;

	

	
	@Before()
	public void setup()
	{
	    //Init MockMvc Object and build
	    mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
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
	
		
	
	 @WithMockUser(value = "test")
	  @Test public void testDeleteRating() throws Exception {
		 Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
		when(ratingRepository.findById(69)).thenReturn(Optional.of(rating));
		doNothing().when(ratingRepository).delete(rating);; 
	  mvc.perform(get("/rating/delete/69")) .andExpect(redirectedUrl("/rating/list")) ;
	  }
	
	  @WithMockUser(value = "test")
	  @Test public void testUpdateRating() throws Exception {
	 
		  Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
			
		  when(ratingRepository.findById(69)).thenReturn(Optional.of(rating));
		mvc.perform(get("/rating/update/69")).andExpect(status().isOk());
}
	
	  @WithMockUser(value = "test")
	  
	  @Test public void testSaveUpdateRating() throws Exception {
		
		  
		  Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
			
		  when(ratingRepository.findById(69)).thenReturn(Optional.of(rating));
		  rating.setFitchRating("fitchRatingUpdated");
		  
		mvc.perform(post("/rating/update/69")).andExpect(status().isOk()); 
	  }
	 
 @WithMockUser(value = "test")
	  
	  @Test public void testFailsSaveUpdates() throws Exception {
		Rating rating = new Rating();
			
		  when(ratingRepository.findById(69)).thenReturn(Optional.of(rating));
		  rating.setFitchRating("fitchRatingUpdated");
		  
		mvc.perform(post("/rating/update/69")).andExpect(status().is(200)); 
	  }
 @WithMockUser(value = "test")
 
 @Test public void testSaveRating() throws Exception {
	
	  
	  Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
		
	  when(ratingRepository.findById(69)).thenReturn(Optional.of(rating));
	  
	  
	mvc.perform(post("/rating/validate")).andExpect(status().isOk()); 
 }
@WithMockUser(value = "test")
 
 @Test public void testFailsSaveRating() throws Exception {
	
	  
	  Rating rating = new Rating();
		
	  when(ratingRepository.findById(69)).thenReturn(Optional.of(rating));
	  
	  
	mvc.perform(post("/rating/validate")).andExpect(status().is(200)); 
 }
}
