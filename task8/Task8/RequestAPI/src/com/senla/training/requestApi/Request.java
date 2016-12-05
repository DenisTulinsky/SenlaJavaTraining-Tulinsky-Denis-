package com.senla.training.requestApi;

import java.io.Serializable;

public class Request implements Serializable {

	/**
	 * Provides a model for sending a request from  Client to Server.
	 */
	private static final long serialVersionUID = 1L;
	private String methodName;
	private Object[] params;

	public Request(String methodName, Object[] params) {
		this.methodName = methodName;
		this.params = params;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
