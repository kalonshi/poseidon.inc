package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidListService {
	/**
	 * Return  all bids
	 *
	 * @param none
	 * @return List<BidList>
	 */
	public List<BidList> getAllbids();
	
	/**
	 *  Add a new Bid
	 *  
	 * @param BidList
	 * @return BidList
	 */
	public void addBid(BidList bid);
	
	/**
	 *  Delete a  Bid
	 *  
	 * @param  Integer id
	 * @return BidList
	 */
	public boolean deleteBid(Integer id);
	
	/**
	 *  Get a  Bid
	 *  
	 * @param  Integer id
	 * @return BidList
	 */
	public BidList getBidList(Integer id);
	
	/**
	 *  Update a  Bid
	 *  
	 * @param  Integer id, BidList bid
	 * @return BidList
	 */
	public BidList updateBidList(Integer id,BidList bid);
}
