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

	@Override
	public List<BidList> bidList() {
		// TODO Auto-generated method stub
		List<BidList> bidLists = bidListRepository.findAll();
		return bidLists;
	}

	@Override
	public boolean deleteBid(Integer id) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		bid.setBidListId(id);
		bidListRepository.save(bid);
		return bid;

	}

	@Override
	public void addBid(BidList bid) {
		// TODO Auto-generated method stub
		bid.setCreationDate(new Timestamp(System.currentTimeMillis()));
		bidListRepository.save(bid);
	}

}
