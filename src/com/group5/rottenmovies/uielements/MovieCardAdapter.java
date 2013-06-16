package com.group5.rottenmovies.uielements;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.group5.rottenmovies.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieCardAdapter extends ArrayAdapter<JSONObject> {
	private Activity activity;
	private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
	List<MovieCardContents> movies = new ArrayList<MovieCardContents>();

	public MovieCardAdapter(Activity a, List<JSONObject> objects) {
		// TODO Auto-generated constructor stub
		super(a, R.layout.movie_card, objects);
		this.activity = a;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for(JSONObject movie : objects ) {
			
			MovieCardContents details;
			try {
				details = new MovieCardContents(movie);
				this.movies.add(details);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
        if(convertView==null) {
            vi = inflater.inflate(R.layout.movie_card, parent, false);
        }
		ImageView poster = (ImageView) vi.findViewById(R.id.moviePoster);
		//imageLoader.displayImage(this.movies.get(position).getPoster(), poster);
		TextView title = (TextView) vi.findViewById(R.id.movieTitle);
		title.setText(this.movies.get(position).getTitle());
		return vi;
	}
	
}
