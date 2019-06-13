package com.jogtrack.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginationLinks {
	// Previous
	@JsonProperty
	private String prev;
	
	// Next
	@JsonProperty
	private String next;
	
	// Self
	@JsonProperty
	private String self;
	
	// Base
	@JsonProperty
	private String base;

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
}
