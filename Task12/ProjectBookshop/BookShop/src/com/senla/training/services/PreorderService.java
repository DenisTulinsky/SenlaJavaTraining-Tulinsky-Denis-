package com.senla.training.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.senla.training.dao.PreorderDAO;
import com.senla.training.hibernateUtils.ConnectorDB;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.model.Preorder;
import com.senla.training.tools.Status;

public class PreorderService implements IPreorderService {
	private IConverterReadableString converterToString;
	private PreorderDAO preorderDao;
	
	public PreorderService(IConverterReadableString converterToString, PreorderDAO preorderDao) {
		this.preorderDao  = preorderDao;
		this.converterToString = converterToString;
	}

	@Override
	public synchronized boolean addPreorder(Preorder preorder) {
			
			preorder.setStatus(Status.ACTIVE);
			Connection cn = ConnectorDB.getInstance().getConnection();
			return preorderDao.add(cn, (Preorder) preorder);

		}

	public synchronized boolean checkPreorders(Integer bookId) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		return preorderDao.updateStatus(cn, bookId, Status.EXECUTED.toString());
				
	}

	@Override
	public synchronized List<String> showAllPreorders(String sortBy) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		List<Preorder> allPreorders = preorderDao.findAll(cn,sortBy);
		List<String> result = new ArrayList<>();
		for (Preorder p : allPreorders) {
			result.add(converterToString.convert(p));
		}
		return result;
	}
}