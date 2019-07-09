package com.springboot.urlShortener;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="URLs")
public class UrlObject {

	@Id
	private String shortid;
	private String url;
	
	public UrlObject() {

	}
	public UrlObject(String shortid, String url) {
		super();
		this.shortid=shortid;
		this.url = url;
	}
	
	public String getShortid() {
		return shortid;
	}
	public void setShortid(String shortid) {
		this.shortid = shortid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
