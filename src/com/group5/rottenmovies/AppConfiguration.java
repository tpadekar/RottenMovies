package com.group5.rottenmovies;

import java.util.HashMap;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class AppConfiguration {
	
	public static final String[] SECTIONS = {"in_theaters", "opening", "upcoming", "top_dvd_rentals", "new_dvds"};//, "upcoming_dvds"};
	public static final HashMap<String, String> SECTION_URL = new HashMap<String, String>();
	public static final HashMap<String, Integer> SECTION_TITLES = new HashMap<String, Integer>();
	public static final HashMap<String, AsyncHttpResponseHandler> PARSERS = new HashMap<String, AsyncHttpResponseHandler>();
	public static final String ROTTENTOMATOES_KEY = "tc9s4bbmmmgmzstjjfxccm49";
	
	public static final String movieDetailsApi = "http://api.rottentomatoes.com/api/public/v1.0/movies/%s.json?apikey="+ROTTENTOMATOES_KEY;
	public static final String movieImagesApi = "http://images.search.yahoo.com/images/view?o=js&native=1&b=0&vm=r&n=20&p=%s";
	
	static {
		SECTION_URL.put("in_theaters", "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey="+ROTTENTOMATOES_KEY);
		SECTION_URL.put("opening", "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey="+ROTTENTOMATOES_KEY);
		SECTION_URL.put("upcoming", "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey="+ROTTENTOMATOES_KEY);
		SECTION_URL.put("top_dvd_rentals", "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/top_rentals.json?apikey="+ROTTENTOMATOES_KEY);
		SECTION_URL.put("new_dvds", "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey="+ROTTENTOMATOES_KEY);
		SECTION_URL.put("upcoming_dvds", "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/upcoming.json?apikey="+ROTTENTOMATOES_KEY);
    }
	
	static {
        SECTION_TITLES.put("in_theaters", R.string.in_theaters);
        SECTION_TITLES.put("opening", R.string.opening);
        SECTION_TITLES.put("upcoming", R.string.upcoming);
        SECTION_TITLES.put("top_dvd_rentals", R.string.top_dvd_rentals);
        SECTION_TITLES.put("new_dvds", R.string.new_dvds);
        SECTION_TITLES.put("upcoming_dvds", R.string.upcoming_dvds);
    }

}
