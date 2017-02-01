package com.senla.training.interfaces;

import java.io.Serializable;
import java.util.Date;

import com.senla.training.tools.Status;

public interface IOrder extends Serializable {
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
	public String getId();
	public void setId(String id);
	public IOrder clone() throws CloneNotSupportedException;
	public String getBookId();
	public void setBookId(String id);
}
