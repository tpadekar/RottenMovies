/**
 * 
 */
package com.group5.rottenmovies;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.group5.rottenmovies.uielements.MovieCastMember;
import com.group5.rottenmovies.uielements.MovieCastView;
import com.group5.rottenmovies.uielements.MovieData;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.novoda.imageloader.core.model.ImageTag;
import com.novoda.imageloader.core.model.ImageTagFactory;

/**
 * @author padekar
 *
 */
public class MovieDetailsResponseHandler extends AsyncHttpResponseHandler {

	private Context context;
	private ScrollView container;
	private RelativeLayout main_container;
	
	/**
	 * 
	 */
	public MovieDetailsResponseHandler(Context context, View container) {
		this.context = context;
		
		this.main_container = (RelativeLayout) container;
		this.container = (ScrollView) main_container.findViewById(R.id.movie_details_container);
	}
	
	@Override
	public void onSuccess(String data) {
		data = data.replace("\n", "").replace("\r", "");
		try {
			JSONObject result = new JSONObject(data);
			MovieData movie = new MovieData(result);
			
			ImageView poster = (ImageView) container.findViewById(R.id.movie_poster);
			
			ImageTagFactory imgfact = ImageTagFactory.newInstance(this.context, R.drawable.poster_default);
			//imgfact.setAnimation(android.R.anim.fade_in);
			
			ImageTag tag = imgfact.build(movie.getPoster(), this.context);
			poster.setTag(tag);
			HomeActivity.getImageManager().getLoader().load(poster);
			
			TextView moreImages = (TextView) container.findViewById(R.id.more_images);
			moreImages.setTag(movie.getTitle() + " movie");
			
			TextView title = (TextView) container.findViewById(R.id.movie_title);
			title.setText(movie.getTitle() + " (" + movie.getYear() + ")");
			
			TextView summary = (TextView) container.findViewById(R.id.movie_summary);
			summary.setText(movie.getDescription());
			
			RatingBar rating = (RatingBar) container.findViewById(R.id.audience_rating);
			rating.setMax(5);
			rating.setRating(movie.getAudienceRating()*5/100);	
		
			
			LinearLayout cast = (LinearLayout) container.findViewById(R.id.movie_cast_container);
			for(MovieCastMember m : movie.getCast()) {
				MovieCastView mca = new MovieCastView(context, m);
				cast.addView(mca.getView());
			}
			
			View v = main_container.findViewById(R.id.movie_details_loading);
			v.setVisibility(View.GONE);
			container.setVisibility(View.VISIBLE);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
