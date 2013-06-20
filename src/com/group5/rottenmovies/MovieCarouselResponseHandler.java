/**
 * 
 */
package com.group5.rottenmovies;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group5.rottenmovies.uielements.MovieCardAdapter;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author padekar
 *
 */
public class MovieCarouselResponseHandler extends AsyncHttpResponseHandler {

	private Context context;
	private LinearLayout parent;
	private View carousel;
	/**
	 * 
	 */
	public MovieCarouselResponseHandler(Context context, View carousel) {
		super();
		this.context = context;
		this.parent = (LinearLayout) parent;
		this.carousel = carousel;
	}
	
	@Override
	public void onSuccess(String data) {
		data = data.replace("\n", "").replace("\r", "");
		
		try {
			JSONObject result = new JSONObject(data);
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();
			JSONArray movies = result.getJSONArray("movies");
			if (movies != null) { 
        	   int len = movies.length();
        	   for (int i=0;i<len;i++){ 
        	    list.add(movies.getJSONObject(i));
        	   } 
        	} 
        	MovieCardAdapter mc = new MovieCardAdapter(context, list);
        	
        	LinearLayout movieCarousel = (LinearLayout) carousel.findViewById(R.id.carousel);
        	for(int i=0; i < mc.getCount(); i++) {
        		movieCarousel.addView(mc.getView(i, null, movieCarousel));
        	}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
