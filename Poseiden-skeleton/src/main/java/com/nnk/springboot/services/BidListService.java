package com.nnk.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
@Transactional
public class BidListService {

	@Autowired
	public BidListRepository bidListRepo;

	public boolean createBidList(BidList bid) {
		try {
			if (bid.getAccount() != null && bid.getType() != null && bid.getBidQuantity() != null) {
				bidListRepo.save(bid);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean updateBid(Integer id, BidList bid) {
		try {
			if (bid.getAccount() != null && bid.getType() != null && bid.getBidQuantity() != null) {
				Optional<BidList> bidToUpdate = bidListRepo.findById(id);
				if (!bid.getAccount().equals(bidToUpdate.get().getAccount()))
					bidToUpdate.get().setAccount(bid.getAccount());
				
				if (!bid.getType().equals(bidToUpdate.get().getType()))
					bidToUpdate.get().setType(bid.getType());
				
				if (bid.getBidQuantity() != bidToUpdate.get().getBidQuantity())
					bidToUpdate.get().setBidQuantity(bid.getBidQuantity());
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
