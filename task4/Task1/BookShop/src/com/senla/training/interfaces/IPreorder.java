package com.senla.training.interfaces;

import java.util.Date;

public interface IPreorder {

	public Boolean getStatus();

	public void setStatus(Boolean status);

	public String getTitle();

	public void setTitle(String title);

	public String getAuthor();

	public void setAuthor(String author);

	public Date getPublishedDate();

	public void setPublishedDate(Date publishedDate);

}
