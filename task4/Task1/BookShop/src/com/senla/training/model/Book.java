package com.senla.training.model;

import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.training.interfaces.IBook;

public class Book implements IBook {

	private String title;
	private String author;
	private Year publishedDate;
	private Boolean inStock;
	private Integer price;
	private Calendar arrivalDate;
	private String description;

	public Book(String title, String author, Year publishedDate, Boolean inStock, Integer price, Calendar arrivalDate,
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
		String[] str = bookString.split(",");
		this.title = str[0];
		this.author = str[1];
		this.publishedDate = Year.of(Integer.parseInt(str[2]));
		this.inStock = Boolean.valueOf(str[3]);
		this.price = Integer.valueOf(str[4]);
		GregorianCalendar arD = new GregorianCalendar();
		arD.setTimeInMillis(Long.valueOf(str[5]));
		this.arrivalDate = arD;
		this.description = str[6];
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Year getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Year publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Calendar getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Calendar arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void printMe() {
		System.out.println("Title: " + this.title + " Author: " + this.author + " Date of publication: "
				+ this.publishedDate + " In Stock: " + this.inStock + " Price: " + this.price + " Arrival Date: "
				+ this.arrivalDate + " Description: " + this.description);

	}

	@Override
	public String bookToString() {
		String str = this.title + "," + this.author + "," + this.publishedDate + "," + this.inStock + "," + this.price
				+ "," + this.arrivalDate.getTimeInMillis() + "," + this.description;
		return str;

	}
}
