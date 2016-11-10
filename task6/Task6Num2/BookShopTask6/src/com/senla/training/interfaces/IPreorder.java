package com.senla.training.interfaces;

import com.senla.training.status.Status;

public interface IPreorder {

	public Status getStatus();

	public void setStatus(Status status);

	public String getTitle();

	public void setTitle(String title);

	public String getAuthor();

	public void setAuthor(String author);

	public Integer getCount();

	public void setCount(Integer count);

	public Integer getId();

	public void setId(Integer id);

	public int hashCode();

	public boolean equals(Object obj);
}
