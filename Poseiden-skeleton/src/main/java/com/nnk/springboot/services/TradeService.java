package com.nnk.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
@Transactional
public class TradeService {
	
	@Autowired
	public TradeRepository tradeRepo;

	public boolean createTrade(Trade trade) {
		try {
			if (trade.getAccount() != null && trade.getType() != null && trade.getBuyQuantity() != null) {
				tradeRepo.save(trade);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean updateTrade(Integer id, Trade trade) {
		try {
			if (trade.getAccount() != null && trade.getType() != null && trade.getBuyQuantity() != null) {
				Optional<Trade> tradeToUpdate = tradeRepo.findById(id);
				if (!trade.getAccount().equals(tradeToUpdate.get().getAccount()))
					tradeToUpdate.get().setAccount(trade.getAccount());
				
				if (!trade.getType().equals(tradeToUpdate.get().getType()))
					tradeToUpdate.get().setType(trade.getType());
				
				if (trade.getBuyQuantity() != tradeToUpdate.get().getBuyQuantity())
					tradeToUpdate.get().setBuyQuantity(trade.getBuyQuantity());
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
