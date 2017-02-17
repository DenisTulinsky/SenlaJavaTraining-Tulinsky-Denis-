package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.senla.training.annotations.Printable;
import com.senla.training.annotations.PrintableObject;
import com.senla.training.annotations.PrintableRef;
import com.senla.training.enums.Status;

@Entity
@Table(name = "orders")
@PrintableObject(name = "Order")
public class Order extends Model implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "auto_inc", strategy = "increment")
	@GeneratedValue(generator = "auto_inc")
	@Column
	@Printable(name = "id", order = 1)
	private Integer id;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "books_orders", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	@PrintableRef(name = "books", isDetailedView = false, order = 6)
	private Set<Book> books = new HashSet<Book>();

	@Column
	@Printable(name = "customer", order = 2)
	private String customer;

	@Column
	@Printable(name = "price", order = 3)
	private Integer price;

	@Column(name = ("exec_date"))
	@Temporal(TemporalType.DATE)
	@Printable(name = "executionDate", order = 4)
	private Date executionDate;

	@Enumerated(EnumType.STRING)
	@Printable(name = "status", order = 5)
	private Status status;

	public Order(Book book, String customer, Integer price, Date executionDate) {
		this.customer = customer;
		this.price = price;
		this.executionDate = executionDate;
	}

	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Order clone() throws CloneNotSupportedException {
		Order clone = (Order) super.clone();
		clone.setId(null);
		clone.books = new HashSet<Book>();
		Iterator<Book> iterator = books.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next().clone();
			clone.books.add(book);
		}
		return clone;
	}
}
