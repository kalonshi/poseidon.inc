package com.nnk.springboot.ServiceTest;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleServiceTest {

	@Autowired
	private RuleNameRepository ruleNameRepository;
	@Autowired
	private RuleNameService ruleNameService;

	@WithMockUser(value = "test")
	@Test
	public void ruleServiceTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		ruleNameService.addRuleName(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name"));

		// Update
		Integer id = rule.getId();
		rule.setName("Rule Name Update");

		rule = ruleNameService.updateRuleName(id, rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameService.ruleNameList();
		Assert.assertTrue(listResult.size() > 0);

		// Delete

		Integer id2 = rule.getId();
		ruleNameService.getRuleName(id2);
		ruleNameService.deleteRuleName(id2);

		Optional<RuleName> ruleList = ruleNameRepository.findById(id2);
		Assert.assertFalse(ruleList.isPresent());

	}
}
