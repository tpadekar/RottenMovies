/**
 * 
 */
package com.group5.rottenmovies;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group5.rottenmovies.uielements.MovieImageData;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.novoda.imageloader.core.model.ImageTag;
import com.novoda.imageloader.core.model.ImageTagFactory;

/**
 * @author padekar
 *
 */
public class MovieImagesResponseHandler extends AsyncHttpResponseHandler {

	private Context context;
	private LinearLayout container;
	private MovieImageData imageData;
	private static LayoutInflater inflater=null;
	
	/**
	 * 
	 */
	public MovieImagesResponseHandler(Context context, View container) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.container = (LinearLayout) container;
	}
	
	@Override
	public void onSuccess(String data) {
		data = data.replace("\n", "").replace("\r", "");
		try {
			JSONObject result = new JSONObject(data);
			
			for(int i = 0; i < result.getJSONArray("results").length(); i++) {
				MovieImageData images = new MovieImageData(result.getJSONArray("results").getJSONObject(i));
				
				View v = inflater.inflate(R.layout.movie_image, null);
				
				ImageTagFactory imgfact = ImageTagFactory.newInstance(this.context, R.drawable.poster_default);
				//imgfact.setAnimation(android.R.anim.fade_in);
				
				ImageView image = (ImageView) v.findViewById(R.id.movie_image);
				ImageTag tag = imgfact.build(images.getImageUrl(), this.context);
				image.setTag(tag);
				HomeActivity.getImageManager().getLoader().load(image);
				
				TextView text = (TextView) v.findViewById(R.id.movie_image_title);
				text.setText(android.text.Html.fromHtml(images.getText()).toString());
				
				v.setOnClickListener(new View.OnClickListener(){
					
					@Override
					public void onClick(View v) {
						Intent i = new Intent(v.getContext(),
								ImageDisplayActivity.class);
						i.putExtra("url", (String) v.getTag());
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						v.getContext().startActivity(i);
						
					}
				});
				
				v.setTag(images.getFullImageUrl());
				
				container.addView(v);
			}
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
