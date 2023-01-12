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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {
	
	@Autowired
	private RuleNameService ruleNameService;
	
	@Autowired
	private RuleNameRepository ruleNameRepo;

    @RequestMapping("/ruleName/list")
    public ModelAndView home(Model model) {
    	ModelAndView mav = new ModelAndView();
		mav.addObject("ruleName", ruleNameRepo.findAll());
		mav.setViewName("/ruleName/list");
		mav.setStatus(HttpStatus.OK);
		return mav;
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public ModelAndView validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (ruleNameService.createRuleName(ruleName)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("ruleName", ruleNameRepo.findAll());
		mav.setViewName("ruleName/list");
		
        return mav;
    }

    @GetMapping("/ruleName/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	ModelAndView mav = new ModelAndView();
    	Optional<RuleName> ruleName = ruleNameRepo.findById(id);
    	if (ruleName != null) {
    		RuleName ruleNameToUpdate = ruleName.get();
    		mav.addObject("ruleName", ruleNameToUpdate);
    		mav.setViewName("ruleName/update");
    		mav.setStatus(HttpStatus.OK);
    	} else {
    		mav.setViewName("ruleName/list");
    		mav.setStatus(HttpStatus.BAD_REQUEST);
    	}
        return mav;
    }

    @PostMapping("/ruleName/update/{id}")
    public ModelAndView updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	ModelAndView mav = new ModelAndView();
		if (ruleNameService.updateRuleName(id, ruleName)) {
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		mav.addObject("ruleName", ruleNameRepo.findAll());
		mav.setViewName("/ruleName/list");
		
        return mav;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	ruleNameRepo.deleteById(id);
        return "redirect:/ruleName/list";
    }
}
