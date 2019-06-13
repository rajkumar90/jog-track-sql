package com.jogtrack.service.contract;

public class FilterCriteria {
	private String attribute;
	private String comparison;
	private String value;
	
	public FilterCriteria(String filterString) {
		String[] arr = filterString.split(" ");
		this.attribute = arr[0];
		this.comparison = arr[1];
		this.value = arr[2];
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getComparison() {
		return comparison;
	}

	public void setComparison(String comparison) {
		this.comparison = comparison;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
