package com.senla.training.properties;

public class Holder {
	
	private Boolean execTag;
	private Integer monthsUnwanted;
	
	public Holder(Boolean execTag, Integer monthsUnwanted) {
		this.execTag = execTag;
		this.monthsUnwanted = monthsUnwanted;
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
}
