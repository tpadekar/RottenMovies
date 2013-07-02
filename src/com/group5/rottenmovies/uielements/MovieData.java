/**
 * 
 */
package com.group5.rottenmovies.uielements;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author padekar
 * 
 */
public class MovieData {

	private String title;
	private String movieID;
	private int year;
	private int audienceRating;
	private String description;
	private String mpaaRating;
	private String runtime;
	private ArrayList<String> genres = new ArrayList<String>();
	private String poster;
	private ArrayList<MovieCastMember> cast = new ArrayList<MovieCastMember>();

	/**
	 * @return the cast
	 */
	public ArrayList<MovieCastMember> getCast() {
		return cast;
	}

	/**
	 * @param cast
	 *            the cast to set
	 */
	public void setCast(ArrayList<MovieCastMember> cast) {
		this.cast = cast;
	}

	/**
	 * @throws JSONException
	 * 
	 */
	public MovieData(JSONObject movie) throws JSONException {
		if (movie.has("title")) {
			this.title = movie.getString("title");
		}
		if (movie.has("posters")) {
			this.poster = movie.getJSONObject("posters").getString("detailed");
		}

		if (movie.has("id")) {
			this.movieID = movie.getString("id");
		}
		if (movie.has("year")) {
			this.year = movie.getInt("year");
		}
		if (movie.has("ratings")) {
			this.audienceRating = movie.getJSONObject("ratings").getInt(
					"audience_score");
		}
		if (movie.has("synopsis")) {
			this.description = movie.getString("synopsis");
		}
		if (movie.has("mpaa_rating")) {
			this.mpaaRating = movie.getString("mpaa_rating");
		}
		if (movie.has("runtime")) {
			this.runtime = movie.getString("runtime");
		}

		if (movie.has("genres")) {
			JSONArray genres = movie.getJSONArray("genres");
			if (genres != null) {
				int len = genres.length();
				for (int i = 0; i < len; i++) {
					this.genres.add(genres.get(i).toString());
				}
			}
		}

		if (movie.has("abridged_cast")) {
			JSONArray castList = movie.getJSONArray("abridged_cast");
			int len = castList.length();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					JSONObject m = castList.getJSONObject(i);
					if (m.has("characters")) {
						JSONArray characters = m.getJSONArray("characters");
						StringBuffer characterNames = new StringBuffer();
						if (characters != null) {
							int count = characters.length();
							for (int j = 0; j < count; j++) {
								characterNames.append(characters.get(j)
										.toString() + "/");
							}
							characterNames
									.setLength(characterNames.length() - 1);
						}
						cast.add(new MovieCastMember(m.getString("name"),
								characterNames.toString()));
					}

				}
			}
		}
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the movieID
	 */
	public String getMovieID() {
		return movieID;
	}

	/**
	 * @param movieID
	 *            the movieID to set
	 */
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the audienceRating
	 */
	public int getAudienceRating() {
		return audienceRating;
	}

	/**
	 * @param audienceRating
	 *            the audienceRating to set
	 */
	public void setAudienceRating(int audienceRating) {
		this.audienceRating = audienceRating;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the mpaaRating
	 */
	public String getMpaaRating() {
		return mpaaRating;
	}

	/**
	 * @param mpaaRating
	 *            the mpaaRating to set
	 */
	public void setMpaaRating(String mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	/**
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * @param runtime
	 *            the runtime to set
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	/**
	 * @return the genres
	 */
	public ArrayList<String> getGenres() {
		return genres;
	}

	/**
	 * @param genres
	 *            the genres to set
	 */
	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}

	/**
	 * @return the poster
	 */
	public String getPoster() {
		return poster;
	}

	/**
	 * @param poster
	 *            the poster to set
	 */
	public void setPoster(String poster) {
		this.poster = poster;
	}

}
