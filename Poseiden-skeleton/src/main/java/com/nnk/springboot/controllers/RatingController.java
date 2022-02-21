package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingSercive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;

@Controller
public class RatingController {
	// TODO: Inject Rating service
	@Autowired
	private RatingSercive ratingService;
	@Autowired
	private RatingRepository ratingRepository;

	private static final Logger logger = LogManager.getLogger(RatingController.class);

	@RequestMapping("/rating/list")
	public String home(Model model) {

		logger.info("Entering  Home method for Rating List");

		List<Rating> allRatings = ratingService.ratingList();
		model.addAttribute("ratings", allRatings);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		logger.info("Entering Add method for a Rating");

		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {

		logger.info("Entering validate method to save a rating");

		if (!result.hasErrors()) {
			ratingService.addRating(rating);
			model.addAttribute("ratings", ratingRepository.findAll());

			return "redirect:/rating/list";

		}

		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering Update method for a Rating: Id Rating to update = " + id);

		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));

		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {

		logger.info("Entering Save Update method for a Rating: Id Rating to Update) = " + id);

		if (result.hasErrors()) {
			return "rating/update";
		}
		ratingService.addRating(rating);
		model.addAttribute("ratings", ratingRepository.findAll());

		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering Delete method for a Rating: Id Rating to delete= " + id);

		Rating ratingTodelede = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		ratingRepository.delete(ratingTodelede);
		model.addAttribute("ratings", ratingRepository.findAll());
		return "redirect:/rating/list";
	}
}
