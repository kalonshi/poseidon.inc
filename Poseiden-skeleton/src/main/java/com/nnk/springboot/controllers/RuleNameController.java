package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

	@Autowired
	private RuleNameService ruleService;
	@Autowired
	private RuleNameRepository ruleNameRepository;

	private static final Logger logger = LogManager.getLogger(RuleNameController.class);

	@RequestMapping("/ruleName/list")
	public String home(Model model) {

		logger.info("Entering home() method for ruleName: List of RuleNames");
		List<RuleName> ruleNames = ruleService.ruleNameList();
		model.addAttribute("ruleNames", ruleNames);
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		logger.info("Entering addRuleForm() method ");
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

		logger.info("Entering validate new Rulename method ");
		if (!result.hasErrors()) {
			ruleService.addRuleName(ruleName);
			logger.info("Success add a new Rulename  ");
			model.addAttribute("ruleNames", ruleNameRepository.findAll());
			return "redirect:/ruleName/list";
		}
		logger.info("Fail to add a new Rulename  ");
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering showUpdateForm method : Id ruleName to Update= " + id);
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));

		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {

		logger.info("Entering save Update Rulename method : Id ruleName to Update= " + id);

		if (result.hasErrors()) {
			logger.info("Fail Update Rulename method : Id ruleName to Update= " + id);

			return "ruleName/update";
		}

		ruleService.updateRuleName(id, ruleName);
		logger.info("Success Update Rulename method : Id ruleName to Update= " + id);

		model.addAttribute("ruleNames", ruleService.ruleNameList());

		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

		logger.info("Entering Delete method for a RuleName: Id RuleName to delete= " + id);

		RuleName RuleNameTodelete = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		ruleNameRepository.delete(RuleNameTodelete);
		model.addAttribute("ruleNames", ruleNameRepository.findAll());
		return "redirect:/ruleName/list";
	}
}
