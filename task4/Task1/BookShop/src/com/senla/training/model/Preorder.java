package com.senla.training.model;

import java.util.Date;

import com.senla.training.interfaces.IPreorder;

public class Preorder implements IPreorder {

	private String title;
	private String author;
	private Date publishedDate;
	private Boolean status = false;

	public Preorder(String title, String author, Date publishedDate) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setPublishedDate(publishedDate);
	}

	public Preorder(String preorderstring) {

	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

}
