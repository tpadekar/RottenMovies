package com.group5.rottenmovies;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings;
import com.novoda.imageloader.core.LoaderSettings.SettingsBuilder;
import com.novoda.imageloader.core.cache.LruBitmapCache;

@SuppressLint("NewApi") 
public class MovieDetailActivity extends Activity {
	
	private String movieId;
	private String movieName;
	public static ImageManager imageManager;

	public static final ImageManager getImageManager() {
		return imageManager;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);
		
		// Set up the Image Loader classes
		int PERCENTAGE_OF_CACHE = 50;
		LoaderSettings settings = new SettingsBuilder()
				.withCacheManager(new LruBitmapCache(this, PERCENTAGE_OF_CACHE))
				.withDisconnectOnEveryCall(true).build(this);
		imageManager = new ImageManager(this, settings);
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        Intent intent = getIntent();
     
        movieName = intent.getStringExtra(HomeActivity.EXTRA_MOVIE_NAME);
        setTitle(movieName);
        
        movieId = intent.getStringExtra(HomeActivity.EXTRA_MOVIE_ID);
        
        try {
			showMovieDetails(movieId, movieName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showMovieDetails(String movieId, String movieName) throws UnsupportedEncodingException {
		View movieContainer = findViewById(R.id.movie_details_page);
		View imagesCarousel = findViewById(R.id.images_container);
		TomatoesAPIClient.getMovieDetails(movieId, new MovieDetailsResponseHandler(this.getApplicationContext(), movieContainer));
		
		TomatoesAPIClient.getMovieImages(URLEncoder.encode("\"" + movieName + "\"" + " movie", "UTF-8"), new MovieImagesResponseHandler(this.getApplicationContext(), imagesCarousel));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Recursively sets a {@link Typeface} to all {@link TextView}s in a
	 * {@link ViewGroup}.
	 */
	public static final void setAppFont(ViewGroup mContainer, Typeface mFont) {
		if (mContainer == null || mFont == null)
			return;

		final int mCount = mContainer.getChildCount();

		// Loop through all of the children.
		for (int i = 0; i < mCount; ++i) {
			final View mChild = mContainer.getChildAt(i);
			if (mChild instanceof TextView) {
				// Set the font if it is a TextView.
				((TextView) mChild).setTypeface(mFont);
			} else if (mChild instanceof ViewGroup) {
				// Recursively attempt another ViewGroup.
				setAppFont((ViewGroup) mChild, mFont);
			}
		}
	}

}
