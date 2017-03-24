package com.senla.training.interfaces;

import java.util.List;

import com.senla.training.model.Preorder;

public interface IPreorderService {

	public boolean addPreorder(Preorder preorder);

	public boolean updateStatus(Integer id);

	public List<Preorder> showAllPreorders(String sortBy);

	public boolean readPreordersFromCsv();

	public boolean writePreordersToCsv();

}
