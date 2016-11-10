package com.senla.training.model;

import java.util.Date;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.status.Status;

public class Order implements IOrder,Cloneable {

	private Integer id;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((executionDate == null) ? 0 : executionDate.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (executionDate == null) {
			if (other.executionDate != null)
				return false;
		} else if (!executionDate.equals(other.executionDate))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		
		return true;
	}
	@Override
	public IOrder clone() throws CloneNotSupportedException{
		IOrder clone = (IOrder) super.clone();
		clone.setId(null);
		return clone;
	} 
}
