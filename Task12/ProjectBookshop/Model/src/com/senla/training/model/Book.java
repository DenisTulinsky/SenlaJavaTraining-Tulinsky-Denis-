package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.senla.training.annotations.Printable;
import com.senla.training.annotations.PrintableObject;
import com.senla.training.enums.Status;

@Entity
@Table(name = "books")
@PrintableObject(name = "Book")
public class Book extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="auto_inc", strategy = "increment")
    @GeneratedValue(generator="auto_inc")
	@Column(name = ("id"))
	@Printable(name = "id", order = 1)
	private Integer id;
	
	@Column
	@Printable(name = "title", order = 2)
	private String title;
	
	@Column
	@Printable(name = "author", order = 3)
	private String author;
	
	@Column(name = ("published_date"))
	@Printable(name = "publishedDate", order = 4)
	private Date publishedDate;
	
	@Enumerated(EnumType.STRING)
	@Printable(name = "inStock", order = 5)
	private Status inStock;
	
	@Column
	@Printable(name = "price", order = 6)
	private Integer price;
	
	@Column(name = ("arrival_date"))
	@Printable(name = "arrivalDate", order = 7)
	private Date arrivalDate;
	
	@Column
	@Printable(name = "description", order = 8)
	private String description;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	public Book() {
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
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Status getInStock() {
		return inStock;
	}
	public void setInStock(Status inStock) {
		this.inStock = inStock;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getDescription() {
		return description;
	}
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
