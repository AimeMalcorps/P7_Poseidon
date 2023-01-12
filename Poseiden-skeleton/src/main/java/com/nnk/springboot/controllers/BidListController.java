package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;


@Controller
public class BidListController {

	@Autowired
	private BidListService bidListService;
	
	@Autowired
	private BidListRepository bidListRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(BidListController.class);

    @RequestMapping("/bidList/list")
    public ModelAndView home(Model model) {
    	logger.info("/bidList/list");
    	ModelAndView mav = new ModelAndView();
		mav.addObject("bidList", bidListRepo.findAll());
		mav.setViewName("/bidList/list");
		mav.setStatus(HttpStatus.OK);
		return mav;
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public ModelAndView validate(@Valid BidList bid, BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (bidListService.createBidList(bid)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("bidList", bidListRepo.findAll());
		mav.setViewName("bidList/list");
		
        return mav;
    }

    @GetMapping("/bidList/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	ModelAndView mav = new ModelAndView();
    	BidList bid = bidListRepo.findByBidListId(id);
    	if (bid != null) {
    		mav.addObject("bidList", bid);
    		mav.setViewName("bidList/update");
    		mav.setStatus(HttpStatus.OK);
    	} else {
    		mav.setViewName("bidList/list");
    		mav.setStatus(HttpStatus.BAD_REQUEST);
    	}
        return mav;
    }

    @PostMapping("/bidList/update/{id}")
    public ModelAndView updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (bidListService.updateBid(id, bidList)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("bidList", bidListRepo.findAll());
		mav.setViewName("/bidList/list");
		
        return mav;
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	bidListRepo.deleteById(id);
        return "redirect:/bidList/list";
    }
}
