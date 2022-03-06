package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

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
public class TradeController {

	@Autowired
	private TradeService tradeService;
	@Autowired
	private TradeRepository tradeRepository;

	private static final Logger logger = LogManager.getLogger(TradeController.class);

	@RequestMapping("/trade/list")
	public String home(Model model) {

		logger.info("Entering home method for trade: List of trades");
		List<Trade> trades = tradeService.tradeList();
		model.addAttribute("trades", trades);
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		logger.info("Entering Add method for a new Trade  ");
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		logger.info("Entering save  new Trade method ");
		if (!result.hasErrors()) {
			tradeService.addTrade(trade);
			logger.info("Success to add a new Trade  ");
			model.addAttribute("trades", tradeService.tradeList());
			return "redirect:/trade/list";
		}
		logger.info("Fail to add a new Trade  ");
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering  Update Trade method : Id Trade to  Update= " + id);

		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));

		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

		logger.info("Entering save Update Trade method : Id Trade  Updated= " + id);

		if (result.hasErrors()) {

			logger.info("Fail to Update Trade  : Id Trade Not Updated= " + id);
			return "trade/update";
		}

		tradeService.updateTrade(id, trade);
		model.addAttribute("trades", tradeService.tradeList());
		logger.info("succes to Update Trade  : Id Trade  Updated= " + id);

		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering Delete method for a Trade: Id Trade to delete= " + id);

		Trade tradeTodelete = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		tradeRepository.delete(tradeTodelete);
		model.addAttribute("trades", tradeRepository.findAll());

		return "redirect:/trade/list";
	}
}
