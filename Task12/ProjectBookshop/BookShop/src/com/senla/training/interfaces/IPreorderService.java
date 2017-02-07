package com.senla.training.interfaces;

import java.util.List;

import com.senla.training.model.Preorder;

public interface IPreorderService {

	public boolean addPreorder(Preorder preorder);

	public boolean checkPreorders(Integer id);

	public List<String> showAllPreorders(String sortBy);

}
