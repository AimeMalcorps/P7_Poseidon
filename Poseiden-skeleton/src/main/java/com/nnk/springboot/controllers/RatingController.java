package com.nnk.springboot.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RatingRepository ratingRepo;

    @RequestMapping("/rating/list")
    public ModelAndView home(Model model) {
    	ModelAndView mav = new ModelAndView();
		mav.addObject("rating", ratingRepo.findAll());
		mav.setViewName("/rating/list");
		mav.setStatus(HttpStatus.OK);
		return mav;
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public ModelAndView validate(@Valid Rating rating, BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (ratingService.createRating(rating)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("rating", ratingRepo.findAll());
		mav.setViewName("rating/list");
		
        return mav;
    }

    @GetMapping("/rating/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	ModelAndView mav = new ModelAndView();
    	Optional<Rating> rating = ratingRepo.findById(id);
    	if (rating != null) {
    		Rating ratingToUpdate = rating.get();
    		mav.addObject("rating", ratingToUpdate);
    		mav.setViewName("rating/update");
    		mav.setStatus(HttpStatus.OK);
    	} else {
    		mav.setViewName("rating/list");
    		mav.setStatus(HttpStatus.BAD_REQUEST);
    	}
        return mav;
    }

    @PostMapping("/rating/update/{id}")
    public ModelAndView updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (ratingService.updateRating(id, rating)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("rating", ratingRepo.findAll());
		mav.setViewName("/rating/list");
		
        return mav;
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
    	ratingRepo.deleteById(id);
        return "redirect:/rating/list";
    }
}
