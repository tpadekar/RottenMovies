package com.group5.rottenmovies.uielements;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieImageData {
	
	private String imageUrl;
	private String text;
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	public MovieImageData(String imageUrl, String text) {
		super();
		this.imageUrl = imageUrl;
		this.text = text;
	}
	public MovieImageData(JSONObject result) throws JSONException {
		this.imageUrl = result.getString("turlL").toString();
		this.text = result.getString("tit");
	}
	

}
