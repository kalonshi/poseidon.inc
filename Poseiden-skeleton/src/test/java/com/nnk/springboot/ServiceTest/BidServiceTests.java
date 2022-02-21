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

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class BidServiceTests {
	@Autowired
	private BidListService bidListService;
	@Autowired
	private BidListRepository bidListRepository;

	@WithMockUser(value = "test")
	@Test
	public void bidListServiceTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		// Save
		bidListService.addBid(bid);
		Assert.assertNotNull(bid.getBidListId());
		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Find
		List<BidList> listResult = bidListService.bidList();
		Assert.assertTrue(listResult.size() > 0);

		// Update

		Integer id2 = bid.getBidListId();
		bidListService.getBidList(id2);
		bid.setBidQuantity(20d);
		bid = bidListService.updateBidList(id2, bid);
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);
		// delete
		Integer id = bid.getBidListId();
		bidListService.deleteBid(id);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assert.assertFalse(bidList.isPresent());

	}
}
