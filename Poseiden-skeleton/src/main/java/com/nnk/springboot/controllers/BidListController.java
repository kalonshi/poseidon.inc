package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

	@Autowired
	private BidListService bidListService;
	@Autowired
	private BidListRepository bidListRepository;
	private static final Logger logger = LogManager.getLogger(BidListController.class);

	@RequestMapping("/bidList/list")
	public String home(Model model) {

		logger.info("Entering home() method: List of bids ");

		List<BidList> bidLists = bidListService.bidList();
		model.addAttribute("bidLists", bidLists);
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {

		logger.info("Entering Add() method for new Bid ");

		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("Entering validate method to save new Bid ");

		if (!result.hasErrors()) {
			bidListService.addBid(bid);
			model.addAttribute("bidLists", bidListRepository.findAll());
			logger.info("Success save new Bid ");

			return "redirect:/bidList/list";
		}
		logger.info("Fail To save new Bid ");

		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering showUpdateForm()  method : Id Bid =" + id);

		BidList bidToUpdate = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));

		model.addAttribute("bidList", bidToUpdate);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

		logger.info("Entering  Save Update() Bid method : Id Bid =" + id);

		if (result.hasErrors()) {
			logger.info("Fail  Save Update() Bid method : Id Bid =" + id);

			return "bidList/update";
		}

		bidListService.updateBidList(id, bidList);
		logger.info("Success  Save Update Bid : Id Bid =" + id);

		model.addAttribute("bidLists", bidListService.bidList());

		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering Delete() method for a Bid: Id Bid = " + id);
		BidList bidListTodelede = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));

		bidListRepository.delete(bidListTodelede);
		model.addAttribute("bidLists", bidListRepository.findAll());

		return "redirect:/bidList/list";
	}
}
