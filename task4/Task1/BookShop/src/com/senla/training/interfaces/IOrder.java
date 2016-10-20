package com.senla.training.interfaces;

import java.util.Date;

public interface IOrder {
	public void setBook(IBook book) ;
	
	public String getCustomer();

	public void setCustomer(String customer) ;

	public Date getExecutionDate() ;
	public void setExecutionDate(Date executionDate) ;
	public Integer getPrice();
	public void setPrice(Integer price) ;
	public Boolean getStatus();
	public void setStatus(Boolean status);

	public IBook getBook();
	
}
