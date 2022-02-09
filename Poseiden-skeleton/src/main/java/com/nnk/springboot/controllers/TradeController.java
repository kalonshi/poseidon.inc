package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
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
		// TODO: find all Trade, add to model

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
		// TODO: check data valid and save to db, after saving return Trade list
		logger.info("Entering save  new Trade method ");
		if (!result.hasErrors() && !trade.getAccount().isEmpty() && !trade.getType().isEmpty()
				&& trade.getBuyQuantity() != null) {
			tradeService.addTrade(trade);

			model.addAttribute("trades", tradeService.tradeList());
			return "redirect:/trade/list";
		}

		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Trade by Id and to model then show to the form

		logger.info("Entering  Update Trade method : Id Trade to  Update= " + id);

		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));

		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Trade and return
		// Trade list
		logger.info("Entering save Update Trade method : Id Trade  Updated= " + id);

		// check required fields
		if (result.hasErrors() && trade.getAccount().isEmpty() && trade.getType().isEmpty()
				&& trade.getBuyQuantity() == null) {
			return "trade/update";
		}

		tradeService.updateTrade(id, trade);
		model.addAttribute("trades", tradeService.tradeList());

		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Trade by Id and delete the Trade, return to Trade list
		logger.info("Entering Delete method for a Trade: Id Trade to delete= " + id);

		Trade tradeTodelete = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		tradeRepository.delete(tradeTodelete);
		model.addAttribute("trades", tradeRepository.findAll());

		return "redirect:/trade/list";
	}
}
