package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.status.Status;

public class Order implements IOrder,Cloneable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String id;
	private IBook book;
	private String customer;
	private Integer price;
	private Date executionDate;
	private Status status;
	
	public Order(IBook book, String customer, Integer price, Date executionDate) {

		this.book = book;
		this.customer = customer;
		this.price = price;
		this.executionDate = executionDate;

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Order( String customer, Date executionDate) {

		
		this.customer = customer;
		this.executionDate = executionDate;

	}

	public Order(String orderstring) {

	}
	
	
	public IBook getBook() {
		return book;
	}

	public void setBook(IBook book) {
		this.book = book;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	@Override
	public IOrder clone() throws CloneNotSupportedException {
		IOrder clone = (IOrder) super.clone();
		
		return clone;
	} 
}
