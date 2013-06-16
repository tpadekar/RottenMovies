package com.group5.rottenmovies.uielements;

import org.json.JSONException;
import org.json.JSONObject;



public class MovieCardContents {
	
	private String poster;
	private String title;
	private String movieID;
	private int year;
	private int audienceRating;
	private String description;
	
	public MovieCardContents(JSONObject movie) throws JSONException {
		this.title = movie.getString("title");
		this.poster = movie.getJSONObject("posters").getString("original");
		this.movieID = movie.getString("id");
		this.year = movie.getInt("year");
		this.audienceRating = movie.getJSONObject("ratings").getInt("audience_score");
		this.description = movie.getString("synopsis");
	}
	
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getAudienceRating() {
		return audienceRating;
	}
	public void setAudienceRating(int audienceRating) {
		this.audienceRating = audienceRating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
