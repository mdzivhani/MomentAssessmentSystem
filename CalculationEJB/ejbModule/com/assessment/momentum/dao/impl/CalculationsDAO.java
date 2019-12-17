package com.assessment.momentum.dao.impl;

import com.assessment.momentum.dao.interfaces.CalculationsDAOLocal;
import com.assessment.momentum.entity.Calculations;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class CalculationsDAOLocal
 */
@Stateless
@LocalBean
public class CalculationsDAO implements CalculationsDAOLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public CalculationsDAO() {
	}

	@Override
	public Calculations addCalculations(Calculations calculations) {
		calculations.setTime(new Date());
		entityManager.persist(calculations);
		return calculations;
	}

	@Override
	public List<Calculations> filterCalculations(int id,String username, Date fromDate,Date toDate) {
		try {
			List<Calculations> calculations = entityManager.createNamedQuery("Calculations.filter", Calculations.class)
					.setParameter("id", id).
					setParameter("username",username)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.getResultList();
			System.out.println(calculations);
			return calculations;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Calculations> findAll() {
		try {
		return entityManager.createNamedQuery("Calculations.findAll", Calculations.class).getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
	
}
