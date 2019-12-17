package com.assessment.momentum.dao.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.assessment.momentum.entity.Calculations;

@Local
public interface CalculationsDAOLocal {
	public Calculations addCalculations(Calculations calculations);
	public List<Calculations> filterCalculations(int id,String username, Date fromDate,Date toDate);
	public List<Calculations> findAll();
	
}
