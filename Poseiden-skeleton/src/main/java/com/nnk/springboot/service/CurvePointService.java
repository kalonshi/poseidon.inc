package com.nnk.springboot.service;

import java.util.List;


import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {
	public List<CurvePoint> curvePointList();
	public void addCurvePoint(CurvePoint curvePoint);
	public CurvePoint getCurvePointById(Integer id);
	public boolean deleteCurvePointById(Integer id);
	public CurvePoint updateCurvePoint(Integer id,CurvePoint curvePoint);
}
