package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidListService {

	public List<BidList> bidList();
	public void addBid(BidList bid);
	public boolean deleteBid(Integer id);
	public BidList getBidList(Integer id);
	public BidList updateBidList(Integer id,BidList bid);
}
