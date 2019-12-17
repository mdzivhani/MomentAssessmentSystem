package com.assessment.momentum.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Calculations
 *
 */
@Entity
@NamedQuery(name = "Calculations.filter",query = "Select c From Calculations c  where  c.username like :search or c.id = :id or"
		+ " c.time between :fromDate and :toDate")
@NamedQuery(name="Calculations.findAll",query ="Select c From Calculations c")
public class Calculations implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String equation;
	private String answer;
	private String username;
	private Date time;
	private static final long serialVersionUID = 1L;

	public Calculations() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquation() {
		return this.equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
