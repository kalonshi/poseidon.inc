package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
@Transactional
public class BidListServiceImpl implements BidListService {
	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * Returns a List of bids
	 *
	 * @param none
	 * @return List<BidList>
	 */
	@Override
	public List<BidList> bidList() {
		// Get the Bids List
		
		List<BidList> bidLists = bidListRepository.findAll();
		return bidLists;
	}

	
	/**
	 * Delete a  bid
	 *
	 * @param Integer id
	 * @return boolean
	 */
	@Override
	public boolean deleteBid(Integer id) {
		// Delete a Bid by id
		Boolean isDeleted = false;
		if (!id.equals(null)) {
			try {
				BidList bidList = bidListRepository.getOne(id);
				bidListRepository.delete(bidList);
				isDeleted = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return isDeleted;
	}

	@Override
	public BidList getBidList(Integer id) {
		// Get a Bid  by id
		BidList bidList = new BidList();
		if (!id.equals(null)) {

			try {
				bidList = bidListRepository.getOne(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return bidList;
	}

	@Override
	public BidList updateBidList(Integer id, BidList bid) {
		// Update a bid 
		bid.setBidListId(id);
		bidListRepository.save(bid);
		return bid;

	}

	@Override
	public void addBid(BidList bid) {
		// Add a bid with a automatic Date of creation 
		bid.setCreationDate(new Timestamp(System.currentTimeMillis()));
		bidListRepository.save(bid);
	}

}
