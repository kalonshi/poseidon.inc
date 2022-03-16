package com.nnk.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;

import com.nnk.springboot.repositories.RuleNameRepository;

@Service
@Transactional
public class RuleNameServiceImpl implements RuleNameService {
	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Override
	public List<RuleName> ruleNameList() {
		// Get rules List
		List<RuleName> ruleNameList = ruleNameRepository.findAll();
		return ruleNameList;
	}

	@Override
	public RuleName addRuleName(RuleName ruleName) {
		// Add rule
		RuleName addRuleName = new RuleName();
		if (!ruleName.equals(null)) {
			ruleNameRepository.save(ruleName);
		}

		return addRuleName;
	}

	@Override
	public boolean deleteRuleName(Integer id) {

		// Delete rule by id

		Boolean isDeleted = false;
		if (id!=null) {
			
				RuleName ruleName = ruleNameRepository.getOne(id);
				ruleNameRepository.delete(ruleName);
				isDeleted = true;
			
		}

		return isDeleted;
	}

	@Override
	public RuleName getRuleName(Integer id) {
		// Get rule by id
		RuleName getRuleName = new RuleName();
		try {
			getRuleName = ruleNameRepository.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getRuleName;
	}

	@Override
	public RuleName updateRuleName(Integer id, RuleName ruleName) {
		// Update rule by id
		ruleName.setId(id);
		ruleNameRepository.save(ruleName);
		return ruleName;

	}

}
