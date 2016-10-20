package com.senla.training.interfaces;

import java.util.Date;

public interface IBook {
	
	
	public String getTitle();
	public void setTitle(String title);
	public String getAuthor();
	public void setAuthor(String author);
	public Date getPublishedDate();
	public void setPublishedDate(Date publishedDate);
	public Boolean getInStock();
	public void setInStock(Boolean inStock);
	public Integer getPrice();
	public void setPrice(Integer price);
	public Date getArrivalDate();
	public void setArrivalDate(Date arrivalDate);
	public String getDescription();
	public void setDescription(String description);
	public Integer getPreorders();
	public void setPreorders(Integer preorders);
}
