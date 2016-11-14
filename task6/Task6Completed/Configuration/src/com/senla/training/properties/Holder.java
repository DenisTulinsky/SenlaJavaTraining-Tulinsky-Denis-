package com.senla.training.properties;

public class Holder {
	
	private Boolean execTag;
	private Integer monthsUnwanted;
	private String serializationPath;
	
	public Holder(Boolean execTag, Integer monthsUnwanted, String serializationPath ) {
		this.execTag = execTag;
		this.monthsUnwanted = monthsUnwanted;
		this.serializationPath = serializationPath;
	}

	public Boolean getExecTag() {
		return execTag;
	}

	public void setExecTag(Boolean execTag) {
		this.execTag = execTag;
	}

	public Integer getMonthsUnwanted() {
		return monthsUnwanted;
	}

	public void setMonthsUnwanted(Integer monthsUnwanted) {
		this.monthsUnwanted = monthsUnwanted;
	}

	public String getSerializationPath() {
		return serializationPath;
	}

	public void setSerializationPath(String serializationPath) {
		this.serializationPath = serializationPath;
	}
}
