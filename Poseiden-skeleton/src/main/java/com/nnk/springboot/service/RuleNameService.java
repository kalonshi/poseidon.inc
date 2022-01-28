package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {
	public List<RuleName> ruleNameList();
	public RuleName addRuleName(RuleName RuleName);
	public boolean deleteRuleName(Integer id);
	public RuleName getRuleName(Integer id);
	public RuleName updateRuleName(Integer id,RuleName RuleName);
}
