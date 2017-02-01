package com.senla.training.model;

import java.io.Serializable;
import java.util.Date;

import com.senla.training.annotations.Printable;
import com.senla.training.annotations.PrintableObject;
import com.senla.training.annotations.PrintableRef;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.tools.Status;

@PrintableObject(name = "Order")
public class Order extends Model implements IOrder, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Printable(name = "id", order = 1)
	private String id;

	@PrintableRef(name = "book", isDetailedView = true, order = 2)
	private IBook book;

	@Printable(name = "customer", order = 3)
	private String customer;

	@Printable(name = "price", order = 4)
	private Integer price;

	@Printable(name = "executionDate", order = 5)
	private Date executionDate;

	@Printable(name = "status", order = 6)
	private Status status;

	private String bookId;

	public Order(IBook book, String customer, Integer price, Date executionDate) {

		this.book = book;
		this.customer = customer;
		this.price = price;
		this.executionDate = executionDate;

	}

	public Order(String id, String customer, Date executionDate, String bookId) {
		this.id = id;
		this.customer = customer;
		this.executionDate = executionDate;
		this.bookId = bookId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Order() {
	}

	public IBook getBook() {
		return book;
	}

	public void setBook(IBook book) {
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
	public IOrder clone() throws CloneNotSupportedException {
		IOrder clone = (IOrder) super.clone();

		return clone;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
}
