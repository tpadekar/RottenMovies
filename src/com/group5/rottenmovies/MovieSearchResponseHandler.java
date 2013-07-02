/**
 * 
 */
package com.group5.rottenmovies;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.group5.rottenmovies.uielements.MovieCardAdapter;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author angelac2
 *
 */
@SuppressLint("NewApi")
public class MovieSearchResponseHandler extends AsyncHttpResponseHandler {

	private Context context;
	private View container;
	/**
	 * 
	 */
	public MovieSearchResponseHandler(Context context, View container) {
		super();
		this.context = context;
		this.container = container;
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
        	        	
        	LinearLayout movieGrid = (LinearLayout) container.findViewById(R.id.searchGridLayout);
        	for(int i=0; i < mc.getCount(); i++) {
        		movieGrid.addView(mc.getView(i, null, movieGrid));
        	}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
