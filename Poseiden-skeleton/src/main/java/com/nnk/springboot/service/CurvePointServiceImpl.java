package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
@Transactional
public class CurvePointServiceImpl implements CurvePointService {
	@Autowired
	private CurvePointRepository curvePointRepository;

	@Override
	public CurvePoint getCurvePointById(Integer id) {
		 // Get a curvePoint  by id
		CurvePoint curvePoint = new CurvePoint();
		if (id!=null) {

			try {
				curvePoint = curvePointRepository.findByCurveId(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return curvePoint;
	}

	@Override
	public boolean deleteCurvePointById(Integer id) {
		//  Delete a curvePoint by id
		Boolean isDeleteCurvePoint = false;
		try {
			CurvePoint curvePointToDelete = getCurvePointById(id);
			curvePointRepository.delete(curvePointToDelete);
			isDeleteCurvePoint = true;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return isDeleteCurvePoint;
	}

	@Override
	public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) {
		//  Update a curvePoint
		curvePoint.setId(id);
		curvePointRepository.save(curvePoint);
		return curvePoint;
		
	}

	@Override
	public void addCurvePoint(CurvePoint curvePoint) {
		// Add a curvePoint
		curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
		curvePointRepository.save(curvePoint);

	}

	@Override
	public List<CurvePoint> curvePointList() {
		// Get the curvePoints List
		List<CurvePoint> curvePointList = curvePointRepository.findAll();
		return curvePointList;
	}
}
