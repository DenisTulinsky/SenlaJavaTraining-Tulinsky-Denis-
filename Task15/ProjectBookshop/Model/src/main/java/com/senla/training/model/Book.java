package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.senla.training.enums.Status;

@Entity
@Table(name = "books")
public class Book extends Model implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "auto_inc", strategy = "increment")
	@GeneratedValue(generator = "auto_inc")
	@Column
	private Integer id;

	@Column
	private String title;

	@Column
	private String author;

	@Column(name = ("published_date"))
	@Temporal(TemporalType.DATE)
	private Date publishedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = ("status"))
	private Status status;

	@Column
	private Integer price;

	@Column(name = ("arrival_date"))
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;

	@Column
	private String description;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "book", orphanRemoval = true)
	@Cascade(value = {CascadeType.REMOVE})
	private Set<Preorder> preorders = new HashSet<Preorder>();

	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
	//@JoinTable(name = "books_orders", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
			//@JoinColumn(name = "book_id")})
	private Set<Order> orders = new HashSet<Order>();

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public Book() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	@Override
	public Book clone() throws CloneNotSupportedException {
		Book clone = (Book) super.clone();
		return clone;
	}
}
