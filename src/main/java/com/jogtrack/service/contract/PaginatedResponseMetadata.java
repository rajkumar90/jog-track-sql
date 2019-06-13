package com.jogtrack.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains metadata regarding the paginated response - For eg. prev link, next link, etc
 * @author raj
 *
 */
public class PaginatedResponseMetadata {
	// Can add other attributes like total records, etc if required
	@JsonProperty
	private PaginationLinks links;
	
	@JsonProperty
	private int numEntries;

	public int getNumEntries() {
		return numEntries;
	}

	public void setNumEntries(int numEntries) {
		this.numEntries = numEntries;
	}

	public PaginationLinks getLinks() {
		return links;
	}

	public void setLinks(PaginationLinks links) {
		this.links = links;
	}
}
