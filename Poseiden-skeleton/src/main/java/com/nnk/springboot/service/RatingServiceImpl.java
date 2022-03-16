package com.nnk.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements RatingSercive {
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> ratingList() {
		// Get the ratings List
		List<Rating> ratingList = ratingRepository.findAll();
		return ratingList;
	}

	@Override
	public Rating addRating(Rating rating) {
		// Add a rating
		ratingRepository.save(rating);
		return rating;
	}

	@Override
	public boolean deleteRating(Integer id) {

		// Delete a rating by id
		Boolean isDeleted = false;
		if (id!=null) {
			
				Rating rating = ratingRepository.getOne(id);
				ratingRepository.delete(rating);
				isDeleted = true;
			
		}

		return isDeleted;
	}

	@Override
	public Rating getRating(Integer id) {

		// Get a rating by id
		Rating rating = new Rating();
		if (id!=null) {

			
				rating = ratingRepository.getOne(id);
			
		}
		return rating;
	}

	@Override
	public Rating updateRating(Integer id, Rating rating) {
		// Update a rating
		rating.setId(id);
		ratingRepository.save(rating);
		return rating;

	}

}
