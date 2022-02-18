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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private TradeService tradeService;
	@WithMockUser(value = "test")
	@Test
	public void tradeServiceTest() {
		Trade trade = new Trade("Trade Account", "Type",10d);

		// Save
		tradeService.addTrade(trade);
		Assert.assertNotNull(trade.getTradeId());
		Assert.assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		Integer id = trade.getTradeId();
		tradeService.getTrade(id);
		trade.setAccount("Trade Account Update");
		tradeService.updateTrade(id, trade);
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeService.tradeList();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		
		  
				  trade=tradeService.getTrade(id)	  ; 
		  
				  tradeService.deleteTrade(id);
		/* tradeRepository.delete(trade); */
		  Optional<Trade> tradeList = tradeRepository.findById(id);
		  Assert.assertFalse(tradeList.isPresent());
		 
	}
}
