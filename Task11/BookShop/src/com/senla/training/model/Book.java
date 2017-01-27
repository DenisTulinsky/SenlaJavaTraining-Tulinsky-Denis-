package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;

import com.senla.training.annotations.Printable;
import com.senla.training.annotations.PrintableObject;
import com.senla.training.interfaces.IBook;
import com.senla.training.tools.Status;
@PrintableObject(name = "Book")
public class Book extends Model implements IBook, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Printable(name = "id",order = 1)
	private String id;
	@Printable(name = "title",order = 2)
	private String title;
	@Printable(name = "author",order = 3)
	private String author;
	@Printable(name = "publishedDate",order = 4)
	private Date publishedDate;
	@Printable(name = "inStock",order = 5)
	private Status inStock;
	@Printable(name = "price",order = 6)
	private Integer price;
	@Printable(name = "arrivalDate",order = 7)
	private Date arrivalDate;
	@Printable(name = "description",order = 8)
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

	public Book() {
		// TODO Auto-generated constructor stub
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	

}
