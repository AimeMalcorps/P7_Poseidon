package com.nnk.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
@Transactional
public class CurveService {
	
	@Autowired
	private CurvePointRepository curvePointRepo;
	
	public boolean createCurvePoint(CurvePoint curvePoint) {
		try {
			if (curvePoint.getCurveId() != null && curvePoint.getTerm() != null && curvePoint.getValue() != null) {
				curvePointRepo.save(curvePoint);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean updateCurvePoint(Integer id, CurvePoint curvePoint) {
		try {
			if (curvePoint.getCurveId() != null && curvePoint.getTerm() != null && curvePoint.getValue() != null) {
				Optional<CurvePoint> curvePointToUpdate = curvePointRepo.findById(id);
				if (curvePoint.getCurveId() != curvePointToUpdate.get().getCurveId())
					curvePointToUpdate.get().setCurveId(curvePoint.getCurveId());
				
				if (curvePoint.getTerm() != curvePointToUpdate.get().getTerm())
					curvePointToUpdate.get().setTerm(curvePoint.getTerm());
				
				if (curvePoint.getValue() != curvePointToUpdate.get().getValue())
					curvePointToUpdate.get().setValue(curvePoint.getValue());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
