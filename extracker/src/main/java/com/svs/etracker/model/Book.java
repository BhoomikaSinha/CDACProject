package com.svs.etracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	private int ISDNNo;
	private String name;
	private String aurthor;
	private int price;
	private int noOfCopies;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getISDNNo() {
		return ISDNNo;
	}
	
	public void setISDNNo(int iSDNNo) {
		ISDNNo = iSDNNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAurthor() {
		return aurthor;
	}
	public void setAurthor(String aurthor) {
		this.aurthor = aurthor;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	
 }
