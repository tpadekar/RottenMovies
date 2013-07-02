package com.group5.rottenmovies.uielements;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.group5.rottenmovies.HomeActivity;
import com.group5.rottenmovies.MovieDetailActivity;
import com.group5.rottenmovies.R;
import com.novoda.imageloader.core.model.ImageTag;
import com.novoda.imageloader.core.model.ImageTagFactory;

public class MovieCardAdapter extends ArrayAdapter<JSONObject> {
	private Context context;
	private static LayoutInflater inflater=null;
	List<MovieCardData> movies = new ArrayList<MovieCardData>();
	
	public final static String EXTRA_MOVIE_ID = "com.group5.rottenmovies.MOVIE_ID";
	public final static String EXTRA_MOVIE_NAME = "com.group5.rottenmovies.MOVIE_NAME";

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
		
		ImageTagFactory imgfact = ImageTagFactory.newInstance(context, R.drawable.poster_default);
		//imgfact.setAnimation(android.R.anim.fade_in);
		
		ImageTag tag = imgfact.build(this.movies.get(position).getPoster(), this.context);
		poster.setTag(tag);
		HomeActivity.getImageManager().getLoader().load(poster);
		
		
		TextView title = (TextView) vi.findViewById(R.id.movieTitle);
		title.setText(this.movies.get(position).getTitle());
		
		RatingBar rating = (RatingBar) vi.findViewById(R.id.movieRating);
		rating.setMax(5);
		rating.setRating(this.movies.get(position).getAudienceRating()*5/100);
		
		vi.setTag(this.movies.get(position).getMovieID());
		
		vi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView movieName = (TextView) v.findViewById(R.id.movieTitle);
				
				Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
				intent.putExtra(EXTRA_MOVIE_ID, (String) v.getTag());
				intent.putExtra(EXTRA_MOVIE_NAME, movieName.getText());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				v.getContext().startActivity(intent);
				
			}
		});
		
		return vi;
	}
	
}
