package com.nnk.springboot.service;

import java.util.List;


import com.nnk.springboot.domain.Rating;

public interface RatingSercive {
	public List<Rating> ratingList();
	public Rating addRating(Rating rating);
	public boolean deleteRating(Integer id);
	public Rating getRating(Integer id);
	public Rating updateRating(Integer id,Rating rating);
}
