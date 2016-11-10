package com.senla.training.model;

import java.util.Date;
import com.senla.training.interfaces.IBook;
import com.senla.training.status.Status;

public class Book implements IBook {
	
	private Integer id;
	private String title;
	private String author;
	private Date publishedDate;
	private Status inStock;
	private Integer price;
	private Date arrivalDate;
	private String description;

	public Book(String title, String author, Date publishedDate, Status inStock, Integer price, Date arrivalDate,
			String description) {

		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
		this.inStock = inStock;
		this.price = price;
		this.arrivalDate = arrivalDate;
		this.description = description;
	}

	public Book(String bookString) {

	}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public Date getPublishedDate() {
		return publishedDate;
	}

	@Override
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public Status getInStock() {
		return inStock;
	}

	@Override
	public void setInStock(Status inStock) {
		this.inStock = inStock;
	}

	@Override
	public Integer getPrice() {
		return price;
	}

	@Override
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public Date getArrivalDate() {
		return arrivalDate;
	}

	@Override
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}
