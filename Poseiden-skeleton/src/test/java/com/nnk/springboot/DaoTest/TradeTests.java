package com.nnk.springboot.DaoTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@WithMockUser(value = "test")
	@Test
	public void tradeTest() {
		Trade trade = new Trade("Trade Account", "Type");

		// Save
		trade = tradeRepository.save(trade);
		Assert.assertNotNull(trade.getTradeId());
		Assert.assertTrue(trade.getAccount().equals("Trade Account"));
		Assert.assertTrue(trade.getType().equals("Type"));
		Assert.assertNull(trade.getBenchmark());
		Assert.assertNull(trade.getBook());
		Assert.assertNull(trade.getDealName());
		Assert.assertNull(trade.getRevisionName());
		Assert.assertNull(trade.getSecurity());
		Assert.assertNull(trade.getSide());
		Assert.assertNull(trade.getSourceListId());
		Assert.assertNull(trade.getStatus());
		Assert.assertNull(trade.getDealType()); 
		Assert.assertNull(trade.getBuyPrice()); 
		Assert.assertNull(trade.getSellPrice()); 
		Assert.assertNull(trade.getSellQuantity()); 
		Assert.assertNull(trade.getTrader()); 

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}
}
