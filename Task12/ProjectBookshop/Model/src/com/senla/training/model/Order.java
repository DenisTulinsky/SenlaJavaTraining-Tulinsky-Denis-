package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@PrintableRef(name = "book", isDetailedView = true, order = 2)
	private Book book;

	@Column
	@Printable(name = "customer", order = 3)
	private String customer;

	@Column
	@Printable(name = "price", order = 4)
	private Integer price;

	@Column(name = ("exec_date"))
	@Printable(name = "executionDate", order = 5)
	private Date executionDate;

	@Enumerated(EnumType.STRING)
	@Printable(name = "status", order = 6)
	private Status status;

	@Column(name = ("book_id"))
	private Integer bookId;

	public Order(Book book, String customer, Integer price, Date executionDate) {

		this.book = book;
		this.customer = customer;
		this.price = price;
		this.executionDate = executionDate;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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

		return clone;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
}
