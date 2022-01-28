package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {
	public List<Trade> tradeList();
	public Trade addTrade(Trade trade);
	public boolean deleteTrade(Integer id);
	public Trade getTrade(Integer id);
	public Trade updateTrade(Integer id,Trade trade);

}
