package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;

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

import java.util.Optional;

import javax.validation.Valid;

@Controller
public class CurveController {

	@Autowired
	private CurveService curveService;

	@Autowired
	private CurvePointRepository curveRepository;

	@RequestMapping("/curvePoint/list")
	public ModelAndView home(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("curvePoint", curveRepository.findAll());
		mav.setViewName("curvePoint/list");
		return mav;
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint curvePoint) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public ModelAndView validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();
		if (curveService.createCurvePoint(curvePoint)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("curvePoint", curveRepository.findAll());
		mav.setViewName("curvePoint/list");
		return mav;
	}

	@GetMapping("/curvePoint/update/{id}")
	public ModelAndView showUpdateForm(@PathVariable("id") Integer id, Model model) {
		ModelAndView mav = new ModelAndView();
		Optional<CurvePoint> curvePoint = curveRepository.findById(id);
    	if (curvePoint != null) {
    		CurvePoint curveToUpdate = curvePoint.get();
    		mav.addObject("curvePoint", curveToUpdate);
    		mav.setViewName("curvePoint/update");
    		mav.setStatus(HttpStatus.OK);
    	} else {
    		mav.setViewName("curvePoint/list");
    		mav.setStatus(HttpStatus.BAD_REQUEST);
    	}
        return mav;
	}

	@PostMapping("/curvePoint/update/{id}")
	public ModelAndView updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		ModelAndView mav = new ModelAndView();
		if (curveService.updateCurvePoint(id, curvePoint)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("curvePoint", curveRepository.findAll());
		mav.setViewName("/curvePoint/list");
		
        return mav;
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		curveRepository.deleteById(id);
		return "redirect:/curvePoint/list";
	}
}
