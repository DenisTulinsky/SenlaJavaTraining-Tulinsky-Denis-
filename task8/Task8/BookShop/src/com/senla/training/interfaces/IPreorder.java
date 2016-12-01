package com.senla.training.interfaces;

import java.io.Serializable;

import com.senla.training.tools.Status;

public interface IPreorder extends Serializable {

	public Status getStatus();

	public void setStatus(Status status);

	public String getTitle();

	public void setTitle(String title);

	public String getAuthor();

	public void setAuthor(String author);

	public Integer getCount();

	public void setCount(Integer count);

	public String getId();

	public void setId(String id);

	public int hashCode();

	public boolean equals(Object obj);
}
