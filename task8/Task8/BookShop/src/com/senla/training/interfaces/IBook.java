package com.senla.training.interfaces;

import java.io.Serializable;
import java.util.Date;

import com.senla.training.tools.Status;

public interface IBook extends Serializable {
	
	
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
	public String getId();
	public void setId(String id);
	
}
