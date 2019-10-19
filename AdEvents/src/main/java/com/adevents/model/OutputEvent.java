package com.adevents.model;

import java.io.Serializable;

public class OutputEvent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4300405100742613324L;
	private String id;
	private String timestamp;
	private String type;
	private String visitorId;
	private String pageUrl;
	private String nextPageUrl;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the visitorId
	 */
	public String getVisitorId() {
		return visitorId;
	}
	/**
	 * @param visitorId the visitorId to set
	 */
	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}
	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}
	/**
	 * @param pageUrl the pageUrl to set
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	/**
	 * @return the nextPageUrl
	 */
	public String getNextPageUrl() {
		return nextPageUrl;
	}
	/**
	 * @param nextPageUrl the nextPageUrl to set
	 */
	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nextPageUrl == null) ? 0 : nextPageUrl.hashCode());
		result = prime * result + ((pageUrl == null) ? 0 : pageUrl.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((visitorId == null) ? 0 : visitorId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputEvent other = (OutputEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nextPageUrl == null) {
			if (other.nextPageUrl != null)
				return false;
		} else if (!nextPageUrl.equals(other.nextPageUrl))
			return false;
		if (pageUrl == null) {
			if (other.pageUrl != null)
				return false;
		} else if (!pageUrl.equals(other.pageUrl))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (visitorId == null) {
			if (other.visitorId != null)
				return false;
		} else if (!visitorId.equals(other.visitorId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OutputEvent [id=" + id + ", timestamp=" + timestamp + ", type=" + type + ", visitorId=" + visitorId
				+ ", pageUrl=" + pageUrl + ", nextPageUrl=" + nextPageUrl + "]";
	}

}
