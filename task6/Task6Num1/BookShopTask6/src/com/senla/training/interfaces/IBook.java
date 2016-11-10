package com.senla.training.interfaces;

import java.util.Date;

import com.senla.training.status.Status;

public interface IBook {
	
	
	public String getTitle();
	public void setTitle(String title);
	public String getAuthor();
	public void setAuthor(String author);
	public Date getPublishedDate();
	public void setPublishedDate(Date publishedDate);
	public Status getInStock();
	public void setInStock(Status inStock);
	public Integer getPrice();
	public void setPrice(Integer price);
	public Date getArrivalDate();
	public void setArrivalDate(Date arrivalDate);
	public String getDescription();
	public void setDescription(String description);
	public Integer getId();
	public void setId(Integer id);
	
}
