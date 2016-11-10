package com.senla.training.interfaces;

import java.util.Date;

import com.senla.training.status.Status;

public interface IOrder {
	public void setBook(IBook book);
	public String getCustomer();
	public void setCustomer(String customer);
	public Date getExecutionDate();
	public void setExecutionDate(Date executionDate);
	public Integer getPrice();
	public void setPrice(Integer price);
	public Status getStatus();
	public void setStatus(Status status);
	public IBook getBook();
	public int hashCode();
	public boolean equals(Object obj);
	public Integer getId();
	public void setId(Integer id);
	public IOrder clone() throws CloneNotSupportedException;
}
