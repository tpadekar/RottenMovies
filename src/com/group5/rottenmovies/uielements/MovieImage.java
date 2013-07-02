package com.group5.rottenmovies.uielements;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7229911132966667169L;
	private String fullUrl;
	private String thumbUrl;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MovieImage(JSONObject json) {
		try {
			this.fullUrl = json.getString("iurl");
			this.thumbUrl = json.getString("turlL");
			this.url = json.getString("url");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}

	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	@Override
	public String toString() {
		return this.thumbUrl;
	}

	public static ArrayList<MovieImage> fromJSONArray(JSONArray imageJsonResults) {
		ArrayList<MovieImage> arrayList = new ArrayList<MovieImage>();
		for (int i = 0; i < imageJsonResults.length(); i++) {
			try {
				arrayList
						.add(new MovieImage(imageJsonResults.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

}
