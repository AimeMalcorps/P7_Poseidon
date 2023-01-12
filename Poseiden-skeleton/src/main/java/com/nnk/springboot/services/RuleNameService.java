package com.nnk.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
@Transactional
public class RuleNameService {
	
	@Autowired
	public RuleNameRepository ruleNameRepo;

	public boolean createRuleName(RuleName ruleName) {
		try {
			if (ruleName.getName() != null && ruleName.getDescription() != null && ruleName.getJson() != null
					&& ruleName.getTemplate() != null && ruleName.getSqlStr() != null && ruleName.getSqlPart() != null) {
				ruleNameRepo.save(ruleName);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean updateRuleName(Integer id, RuleName ruleName) {
		try {
			if (ruleName.getName() != null && ruleName.getDescription() != null && ruleName.getJson() != null
					&& ruleName.getTemplate() != null && ruleName.getSqlStr() != null && ruleName.getSqlPart() != null) {
				
				Optional<RuleName> ruleNameToUpdate = ruleNameRepo.findById(id);
				
				if (!ruleName.getName().equals(ruleNameToUpdate.get().getName()))
					ruleNameToUpdate.get().setName(ruleName.getName());
				
				if (!ruleName.getDescription().equals(ruleNameToUpdate.get().getDescription()))
					ruleNameToUpdate.get().setDescription(ruleName.getDescription());
				
				if (!ruleName.getJson().equals(ruleNameToUpdate.get().getJson()))
					ruleNameToUpdate.get().setJson(ruleName.getJson());
				
				if (!ruleName.getTemplate().equals(ruleNameToUpdate.get().getTemplate()))
					ruleNameToUpdate.get().setTemplate(ruleName.getTemplate());
				
				if (!ruleName.getSqlStr().equals(ruleNameToUpdate.get().getSqlStr()))
					ruleNameToUpdate.get().setSqlStr(ruleName.getSqlStr());
				
				if (!ruleName.getSqlPart().equals(ruleNameToUpdate.get().getSqlPart()))
					ruleNameToUpdate.get().setSqlPart(ruleName.getSqlPart());
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
