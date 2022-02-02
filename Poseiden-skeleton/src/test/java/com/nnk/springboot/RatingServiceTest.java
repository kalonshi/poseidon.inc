package com.nnk.springboot;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingSercive;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private RatingSercive ratingService;
	@Test
	public void ratingServiceTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		ratingService.addRating(rating);
		Assert.assertNotNull(rating.getId());
		Assert.assertTrue(rating.getOrderNumber() == 10);

		// Update
		 Integer id = rating.getId();
		  rating.setOrderNumber(20); 
		  ratingService.updateRating(id, rating);
		  Assert.assertTrue(rating.getOrderNumber() == 20);
		 

		// Find
		List<Rating> listResult = ratingService.ratingList();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		
		  Integer id2 = rating.getId(); ratingService.deleteRating(id2); Optional<Rating>
		  ratingList = ratingRepository.findById(id);
		  Assert.assertFalse(ratingList.isPresent());
		 
	}
}
