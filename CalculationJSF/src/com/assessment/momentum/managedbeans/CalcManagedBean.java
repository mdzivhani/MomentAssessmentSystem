package com.assessment.momentum.managedbeans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;

import com.assessment.momentum.dao.interfaces.CalculationsDAOLocal;
import com.assessment.momentum.entity.Calculations;
import com.assessment.momentum.services.AdvancedCalculations;
import com.assessment.momentum.services.BasicCalculations;

@SuppressWarnings("deprecation")
@ManagedBean(name = "calcMB")
public class CalcManagedBean {
	@EJB
	private CalculationsDAOLocal calculationsDAOLocal;
	@ManagedProperty(value = "#{userMB}")
	UserManagedBean userManagedBean;

	private int id;
	private String username;
	private String equation;
	private String answer;
	private Date time;
	private double number1;
	private String operation;
	private double number2;
	private int filter;
	private String fromDate;
	private String toDate;
	private List<Calculations> calculations;
	private String error;

	public CalcManagedBean() {
	}
	
	public void setUser(String username) {
		this.username = username;
	}
	

	public void basicCalculations() {
		Calculations calculations = new Calculations();
		BasicCalculations basicCalculations = new BasicCalculations();

		if (this.operation.length() != 1) {
			this.error = "operation must be one character";
		} else if (!this.operation.equals("*") && !this.operation.equals("/") && !this.operation.equals("-") && !this.operation.equals("+")) {
			this.error = "operation character must only be (*,+,-,/)";
		} else {
			this.error = "";
			double ans = basicCalculations.basicCalculator(this.number1, this.operation, this.number2);
			calculations.setAnswer(String.valueOf(ans));
			calculations.setUsername(this.username);
			calculations.setEquation(
					String.valueOf(this.number1) + " " + this.operation + " " + String.valueOf(this.number2));
			addCalculations(calculations);
		}
	}

	public void advancedCalculations() {
		AdvancedCalculations advancedCalculations = new AdvancedCalculations();
		Calculations calculations = new Calculations();
		calculations.setAnswer(advancedCalculations.advancedCalculator(this.equation));
		calculations.setUsername(this.username);
		calculations.setEquation(this.equation);
		addCalculations(calculations);
	}

	public void populateFields(Calculations calculations) {
		this.id = calculations.getId();
		this.username = calculations.getUsername();
		this.equation = calculations.getEquation();
		this.answer = calculations.getAnswer();
		this.time = calculations.getTime();
	}

	public void addCalculations(Calculations calculations) {
		populateFields(calculationsDAOLocal.addCalculations(calculations));
	}

	public void findAll() {
		this.filter = 0;
		this.calculations = calculationsDAOLocal.findAll();
	}

	public void filterCalc() {
		this.filter = 1;
		System.out.println("Is here");
		Date tDate = new Date(this.toDate);
		Date fDate = new Date(this.fromDate);
		this.calculations = calculationsDAOLocal.filterCalculations(this.id, this.username, fDate, tDate);
	}

	public List<Calculations> getCalculations() {
		return calculations;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setCalculations(List<Calculations> calculations) {
		this.calculations = calculations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getNumber1() {
		return number1;
	}

	public void setNumber1(double number1) {
		this.number1 = number1;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getNumber2() {
		return number2;
	}

	public void setNumber2(double number2) {
		this.number2 = number2;
	}

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
