package com.nnk.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
@Transactional
public class CurvePointServiceImpl implements CurvePointService{
@Autowired
private CurvePointRepository curvePointRepository;

@Override
public CurvePoint getCurvePointById(Integer id) {
	// TODO Auto-generated method stub
	CurvePoint curvePoint=new CurvePoint();
	if(!id.equals(null)) {
		
	
		try {
			curvePoint=curvePointRepository.findByCurveId(id);
		}
		 catch (Exception e) {
				// TODO: handle exception
			}
	}
	return curvePoint;
}

@Override
public boolean deleteCurvePointById(Integer id) {
	// TODO Auto-generated method stub
	Boolean isDeleteCurvePoint=false;
	try {
		CurvePoint curvePointToDelete=getCurvePointById(id);
		curvePointRepository.delete(curvePointToDelete);
		isDeleteCurvePoint=true;
	
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	
	return isDeleteCurvePoint;
}

@Override
public CurvePoint updateCurvePoint(Integer id,CurvePoint curvePoint) {
	// TODO Auto-generated method stub
	curvePoint.setId(id);
	curvePointRepository.save(curvePoint);
	return curvePoint;
		/*
		 * CurvePoint updateCurvePoint=new CurvePoint(); try {
		 * updateCurvePoint=getCurvePointById(id); updateCurvePoint=curvePoint;
		 * curvePointRepository.save(updateCurvePoint); } catch (Exception e) { // TODO:
		 * handle exception }
		 * 
		 * return updateCurvePoint;
		 */
}

@Override
public void addCurvePoint(CurvePoint curvePoint) {
	// TODO Auto-generated method stub
	curvePointRepository.save(curvePoint);
	
}

@Override
public List<CurvePoint> curvePointList() {
	
	List<CurvePoint> curvePointList= curvePointRepository.findAll();
	return curvePointList;
}
}
