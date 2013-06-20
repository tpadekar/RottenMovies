package com.group5.rottenmovies.uielements;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.group5.rottenmovies.HomeActivity;
import com.group5.rottenmovies.R;
import com.novoda.imageloader.core.model.ImageTag;
import com.novoda.imageloader.core.model.ImageTagFactory;

public class MovieCardAdapter extends ArrayAdapter<JSONObject> {
	private Context context;
	private static LayoutInflater inflater=null;
	List<MovieCardData> movies = new ArrayList<MovieCardData>();

	public MovieCardAdapter(Context context, List<JSONObject> objects) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.movie_card, objects);
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for(JSONObject movie : objects ) {
			
			MovieCardData details;
			try {
				details = new MovieCardData(movie);
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
		
		ImageTagFactory imgfact = ImageTagFactory.newInstance(125, 175, R.drawable.poster_default);
		//imgfact.setAnimation(android.R.anim.fade_in);
		
		ImageTag tag = imgfact.build(this.movies.get(position).getPoster(), this.context);
		poster.setTag(tag);
		HomeActivity.getImageManager().getLoader().load(poster);
		
		
		TextView title = (TextView) vi.findViewById(R.id.movieTitle);
		title.setText(this.movies.get(position).getTitle());
		
		RatingBar rating = (RatingBar) vi.findViewById(R.id.movieRating);
		rating.setMax(5);
		rating.setRating(this.movies.get(position).getAudienceRating()*5/100);
		
		return vi;
	}
	
}
