package com.senla.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senla.training.model.Model;

public abstract class AbstractDAO<T extends Model> {

	protected Connection connection;

	public AbstractDAO(Connection connection) {
		this.connection = connection;
	}

	public List<T> findAll(String sortBy) {
		List<T> listObjects = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = getQueryAll(sortBy);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				T model = createObject(resultSet);
				listObjects.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return listObjects;
	}

	public T findEntityById(String id) {
		T entity = null;
		PreparedStatement ps = null;
		try {
			ps = getQueryById(id);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				entity = createObject(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return entity;
	}

	public void updateStatus(String id, String status) {
		PreparedStatement ps = null;
		try {
			ps = getQueryUpdateStatus(id, status);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

	}

	public boolean add(T entity) {

		PreparedStatement ps = null;
		boolean flag = false;
		try {
			ps = getQueryAdd(entity);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return flag;
	}

	public abstract PreparedStatement getQueryAdd(T entity);

	public abstract T createObject(ResultSet resultSet);

	public abstract PreparedStatement getQueryUpdateStatus(String id, String status);

	public abstract PreparedStatement getQueryAll(String sortBy);

	public abstract PreparedStatement getQueryById(String id);

	public void close(PreparedStatement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}