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
		// TODO Auto-generated method stub
		List<RuleName> ruleNameList=ruleNameRepository.findAll();
		return ruleNameList;
	}

	@Override
	public RuleName addRuleName(RuleName ruleName) {
		// TODO Auto-generated method stub
		RuleName addRuleName= new RuleName();
		if(!ruleName.equals(null)) {
		ruleNameRepository.save(ruleName);
		}
		
		return addRuleName;
	}

	@Override
	public boolean deleteRuleName(Integer id) {
		Boolean isDeleted= false;
		if(!id.equals(null)) {
			try {
				RuleName ruleName=ruleNameRepository.getOne(id);
				ruleNameRepository.delete(ruleName);
				isDeleted= true;
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
		
		return isDeleted;
	}

	@Override
	public RuleName getRuleName(Integer id) {
		// TODO Auto-generated method stub
		RuleName getRuleName=new RuleName();
		try {
			getRuleName=ruleNameRepository.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
		}  
		return getRuleName;
	}

	@Override
	public RuleName updateRuleName(Integer id,RuleName ruleName) {
		// TODO Auto-generated method stub
		ruleName.setId(id);
		ruleNameRepository.save(ruleName);
		
		return ruleName;
		
	
	}

}
