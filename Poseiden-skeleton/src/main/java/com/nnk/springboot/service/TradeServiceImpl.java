package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
@Service
@Transactional
public class TradeServiceImpl implements TradeService{
@Autowired
private TradeRepository tradeRepository;
	@Override
	public List<Trade> tradeList() {
		// TODO Auto-generated method stub
		List<Trade> tradeList=tradeRepository.findAll();
		return tradeList;
	}

	@Override
	public Trade addTrade(Trade trade) {
		// TODO Auto-generated method stub
		if(!trade.equals(null)) {
			trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
		tradeRepository.save(trade);
		}
		return trade;
	}

	@Override
	public boolean deleteTrade(Integer id) {
		// TODO Auto-generated method stub
		boolean isDelete=false;
		try {
			tradeRepository.deleteById(id); 
			 isDelete=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isDelete;
	}

	@Override
	public Trade getTrade(Integer id) {
		// TODO Auto-generated method stub
		Trade getTrade=new Trade();
		try {
			getTrade=tradeRepository.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getTrade;
	}

	@Override
	public Trade updateTrade(Integer id, Trade trade) {
		// TODO Auto-generated method stub
		
		trade.setTradeId(id);
		tradeRepository.save(trade);
		return trade;
		
	}

}
