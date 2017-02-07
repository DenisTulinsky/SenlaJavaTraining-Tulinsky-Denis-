package com.senla.training.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;

import com.senla.training.annotations.Printable;
import com.senla.training.annotations.PrintableObject;
import com.senla.training.annotations.PrintableRef;
import com.senla.training.enums.Status;

@PrintableObject(name = "Preorder")
public class Preorder extends Model implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "auto_inc", strategy = "increment")
	@GeneratedValue(generator = "auto_inc")
	@Column
	@Printable(name = "id", order = 1)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@PrintableRef(name = "book", isDetailedView = true, order = 7)///annotation order printout
	private Book book;
	
	@Column
	@Printable(name = "title", order = 2)
	private String title;

	@Column
	@Printable(name = "author", order = 3)
	private String author;

	@Column
	@Printable(name = "count", order = 4)
	private Integer count;

	@Enumerated(EnumType.STRING)
	@Printable(name = "status", order = 5)
	private Status status;

	@Column(name=("book_id"))
	@Printable(name = "bookID", order = 6)
	private Integer bookId;

	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preorder other = (Preorder) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;

		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;

		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

}
