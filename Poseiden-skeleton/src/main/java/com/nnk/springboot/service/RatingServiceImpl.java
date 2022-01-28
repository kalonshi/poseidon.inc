package com.nnk.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements RatingSercive{
@Autowired
private RatingRepository ratingRepository;
	@Override
	public List<Rating> ratingList() {
		// TODO Auto-generated method stub
		List<Rating> ratingList=ratingRepository.findAll();
		return ratingList;
	}

	@Override
	public Rating addRating(Rating rating) {
		// TODO Auto-generated method stub
		ratingRepository.save(rating);
		return rating;
	}

	@Override
	public boolean deleteRating(Integer id) {
		Boolean isDeleted= false;
		if(!id.equals(null)) {
			try {
				Rating rating=ratingRepository.getOne(id);
				ratingRepository.delete(rating);
				isDeleted= true;
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
		
		return isDeleted;
	}

	@Override
	public Rating getRating(Integer id) {
		Rating rating=new Rating();
		if(!id.equals(null)) {
			
		
			try {
		 rating=ratingRepository.getOne(id);
			}
			 catch (Exception e) {
					// TODO: handle exception
				}
		}
		return rating;
	}

	@Override
	public Rating updateRating(Integer id,Rating rating) {
		// TODO Auto-generated method stub
		rating.setId(id);
		ratingRepository.save(rating);
		return rating;
		/*
		 * Rating updateRating=new Rating(); try { updateRating=getRating( id);
		 * updateRating=rating; ratingRepository.save(updateRating); } catch (Exception
		 * e) { // TODO: handle exception }
		 * 
		 * return updateRating;
		 */
	}

}
