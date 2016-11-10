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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((arrivalDate == null) ? 0 :
	 * arrivalDate.hashCode()); result = prime * result + ((author == null) ? 0
	 * : author.hashCode()); result = prime * result + ((description == null) ?
	 * 0 : description.hashCode()); result = prime * result + ((inStock == null)
	 * ? 0 : inStock.hashCode()); result = prime * result + ((price == null) ? 0
	 * : price.hashCode()); result = prime * result + ((publishedDate == null) ?
	 * 0 : publishedDate.hashCode()); result = prime * result + ((title == null)
	 * ? 0 : title.hashCode()); return result; }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; Book other = (Book) obj; if (arrivalDate == null) { if
	 * (other.arrivalDate != null) return false; } else if
	 * (!arrivalDate.equals(other.arrivalDate)) return false; if (author ==
	 * null) { if (other.author != null) return false; } else if
	 * (!author.equals(other.author)) return false; if (description == null) {
	 * if (other.description != null) return false; } else if
	 * (!description.equals(other.description)) return false; if (inStock ==
	 * null) { if (other.inStock != null) return false; } else if
	 * (!inStock.equals(other.inStock)) return false; if (price == null) { if
	 * (other.price != null) return false; } else if
	 * (!price.equals(other.price)) return false; if (publishedDate == null) {
	 * if (other.publishedDate != null) return false; } else if
	 * (!publishedDate.equals(other.publishedDate)) return false; if (title ==
	 * null) { if (other.title != null) return false; } else if
	 * (!title.equals(other.title)) return false; return true; }
	 */

}
