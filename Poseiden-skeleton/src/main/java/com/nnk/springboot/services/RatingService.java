package com.nnk.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
@Transactional
public class RatingService {
	
	@Autowired
	public RatingRepository ratingRepo;
	
	public boolean createRating(Rating rating) {
		try {
			if (rating.getMoodysRating() != null && rating.getFitchRating() != null 
					&& rating.getOrderNumber() != null && rating.getSandPRating() != null) {
				ratingRepo.save(rating);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean updateRating(Integer id, Rating rating) {
		try {
			if (rating.getMoodysRating() != null && rating.getFitchRating() != null 
					&& rating.getOrderNumber() != null && rating.getSandPRating() != null) {
				Optional<Rating> ratingToUpdate = ratingRepo.findById(id);
				
				if (!rating.getMoodysRating().equals(ratingToUpdate.get().getMoodysRating()))
					ratingToUpdate.get().setMoodysRating(rating.getMoodysRating());
				
				if (!rating.getFitchRating().equals(ratingToUpdate.get().getFitchRating()))
					ratingToUpdate.get().setFitchRating(rating.getFitchRating());
				
				if (!rating.getSandPRating().equals(ratingToUpdate.get().getSandPRating()))
					ratingToUpdate.get().setSandPRating(rating.getSandPRating());
				
				if (rating.getOrderNumber() != ratingToUpdate.get().getOrderNumber())
					ratingToUpdate.get().setOrderNumber(rating.getOrderNumber());
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
