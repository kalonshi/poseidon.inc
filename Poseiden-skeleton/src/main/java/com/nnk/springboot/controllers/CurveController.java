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
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.web.bind.annotation.GetMapping;
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

		logger.info("Entering Validate() method for a CurvePoint");

		if (!result.hasErrors()) {

			curvePointdervice.addCurvePoint(curvePoint);
			model.addAttribute("curvePoints", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";
		}
		logger.info("Success Add a new CurvePoint");
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Entering showUpdateForm() method to get a CurvePoint: Id CurbePoint = " + id);

		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));

		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {

		logger.info("Entering  save Update for a CurvePoint: Id CurvePoint = " + id);

		if (result.hasErrors()) {
			logger.info("Fail  save Update for a CurvePoint: Id CurvePoint = " + id);

			return "curvePoint/update";
		}

		curvePointdervice.updateCurvePoint(id, curvePoint);
		logger.info("Success save Update for a CurvePoint: Id CurvePoint = " + id);

		model.addAttribute("curvePoints", curvePointRepository.findAll());

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering Delete method for a CurvePoint: Id CurvePoint = " + id);
		CurvePoint curvePointTodelede = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		curvePointRepository.delete(curvePointTodelede);
		model.addAttribute("curvePoints", curvePointdervice.curvePointList());
		return "redirect:/curvePoint/list";
	}
}
