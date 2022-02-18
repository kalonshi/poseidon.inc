package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CurveController {
	// TODO: Inject Curve Point service
	@Autowired
	private CurvePointService curvePointdervice;
	@Autowired
	private CurvePointRepository curvePointRepository;

	private static final Logger logger = LogManager.getLogger(CurveController.class);

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		// TODO: find all Curve Point, add to model
		logger.info("Entering home() method :CurvePoint List");

		model.addAttribute("curvePoints", curvePointdervice.curvePointList());
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		logger.info("Entering Add() method for a new CurvePoint ");

		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Curve list
		logger.info("Entering Save method for a CurvePoint");
		/*
		 * Double term = curvePoint.getTerm(); Double value = curvePoint.getValue();
		 */
		if (!result.hasErrors() ) {

			curvePointdervice.addCurvePoint(curvePoint);
			model.addAttribute("curvePoints", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";
		}
		
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get CurvePoint by Id and to model then show to the form

		logger.info("Entering Update() method for a CurvePoint: Id CurbePoint = " + id);

		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));

		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update Curve and return
		// Curve list
		logger.info("Entering Update method for a CurvePoint: Id CurvePoint = " + id);

		// check required fields
		if (result.hasErrors() ) {
			return "curvePoint/update";
		}

		curvePointdervice.updateCurvePoint(id, curvePoint);
		model.addAttribute("curvePoints", curvePointRepository.findAll());

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Curve by Id and delete the Curve, return to Curve list
		logger.info("Entering Delete method for a CurvePoint: Id CurvePoint = " + id);
		CurvePoint curvePointTodelede = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		curvePointRepository.delete(curvePointTodelede);
		model.addAttribute("curvePoints", curvePointdervice.curvePointList());
		return "redirect:/curvePoint/list";
	}
}
