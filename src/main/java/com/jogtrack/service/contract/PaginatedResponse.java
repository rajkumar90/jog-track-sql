package com.jogtrack.service.contract;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginatedResponse<T> {
	@JsonProperty
	private PaginatedResponseMetadata metadata;
	
	@JsonProperty
	private List<T> payload;

	public PaginatedResponseMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(PaginatedResponseMetadata metadata) {
		this.metadata = metadata;
	}

	public List<T> getPayload() {
		return payload;
	}

	public void setPayload(List<T> payload) {
		this.payload = payload;
	}

}
