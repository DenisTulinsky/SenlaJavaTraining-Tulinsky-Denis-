package com.senla.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.model.Model;

public abstract class AbstractDAO<T extends Model> {

	private final Logger log = Logger.getLogger(AbstractDAO.class);

	public List<T> findAllLazy(Connection cn, String sortBy) {
		List<T> listObjects = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(getQueryAllLazy() + sortBy);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				T entity = createObjectLazyInit(resultSet);
				listObjects.add(entity);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return listObjects;
	}

	public List<T> findAll(Connection cn, String sortBy) {
		List<T> listObjects = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(getQueryAll() + sortBy);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				T entity = createObject(resultSet);
				listObjects.add(entity);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return listObjects;
	}

	public T findEntityById(Connection cn, String id) {
		T entity = null;
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(getQueryById());
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				entity = createObject(resultSet);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return entity;
	}

	public boolean updateStatus(Connection cn, String id, String status) {
		PreparedStatement ps = null;
		Boolean flag = false;
		try {
			ps = cn.prepareStatement(getQueryUpdateStatus());
			ps.setString(1, status);
			ps.setString(2, id);
			int row = ps.executeUpdate();
			if (row>=1) {
				flag = true;
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return flag;
	}

	public boolean add(Connection cn, T entity) {
		PreparedStatement ps = null;
		Boolean flag = false;
		try {
			ps = cn.prepareStatement(getQueryAdd());
			setInsertValues(ps, entity);
			Integer row = ps.executeUpdate();
			if (row.equals(1)) {
				flag = true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return flag;
	}

	public abstract PreparedStatement setInsertValues(PreparedStatement ps, T entity);

	public abstract String getQueryAdd();

	public abstract T createObject(ResultSet resultSet);

	public abstract T createObjectLazyInit(ResultSet resultSet);

	public abstract String getQueryUpdateStatus();

	public abstract String getQueryAll();

	public abstract String getQueryAllLazy();

	public abstract String getQueryById();

	public void close(PreparedStatement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}