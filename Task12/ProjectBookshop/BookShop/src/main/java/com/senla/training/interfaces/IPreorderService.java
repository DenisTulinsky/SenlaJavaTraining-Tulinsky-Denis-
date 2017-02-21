package com.senla.training.interfaces;

import java.util.List;

public interface IPreorderService {

	public boolean addPreorder(Integer bookId);

	public boolean updateStatus(Integer id);

	public List<String> showAllPreorders(String sortBy);

	public boolean readPreordersFromCsv();

	public boolean writePreordersToCsv();

}
