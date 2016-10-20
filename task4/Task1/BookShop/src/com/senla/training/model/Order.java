package com.senla.training.model;

import java.util.Date;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;

public class Order implements IOrder {

	private IBook book;
	private String customer;
	private Integer price;
	private Date executionDate;
	private Boolean status = false;

	public Order(IBook book, String customer, Integer price, Date executionDate) {

		this.book = book;
		this.customer = customer;
		this.price = price;
		this.executionDate = executionDate;

	}

	public Order(String orderstring) {

	}

	public Order() {

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
