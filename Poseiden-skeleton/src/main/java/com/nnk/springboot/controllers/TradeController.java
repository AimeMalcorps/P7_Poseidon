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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@Controller
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private TradeRepository tradeRepo;

    @RequestMapping("/trade/list")
    public ModelAndView home(Model model) {
    	ModelAndView mav = new ModelAndView();
		mav.addObject("trade", tradeRepo.findAll());
		mav.setViewName("/trade/list");
		mav.setStatus(HttpStatus.OK);
		return mav;
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public ModelAndView validate(@Valid Trade trade, BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (tradeService.createTrade(trade)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("trade", tradeRepo.findAll());
		mav.setViewName("trade/list");
		
        return mav;
    }

    @GetMapping("/trade/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	ModelAndView mav = new ModelAndView();
    	Optional<Trade> trade = tradeRepo.findById(id);
    	if (trade != null) {
    		Trade tradeToUpdate = trade.get();
    		mav.addObject("trade", tradeToUpdate);
    		mav.setViewName("trade/update");
    		mav.setStatus(HttpStatus.OK);
    	} else {
    		mav.setViewName("trade/list");
    		mav.setStatus(HttpStatus.BAD_REQUEST);
    	}
        return mav;
    }

    @PostMapping("/trade/update/{id}")
    public ModelAndView updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (tradeService.updateTrade(id, trade)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("trade", tradeRepo.findAll());
		mav.setViewName("/trade/list");
		
        return mav;
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeRepo.deleteById(id);
        return "redirect:/trade/list";
    }
}
