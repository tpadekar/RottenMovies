package com.group5.rottenmovies;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings;
import com.novoda.imageloader.core.LoaderSettings.SettingsBuilder;
import com.novoda.imageloader.core.cache.LruBitmapCache;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi") 

public class SearchActivity extends Activity {
	
	public static ImageManager imageManager;
	EditText etQuery;
	//GridView gvResults;
	Button btnSearch;
	
	public static final ImageManager getImageManager() {
		return imageManager;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_search);
	    setupViews();

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
	}
	
	public void setupViews() {
	 	etQuery = (EditText) findViewById(R.id.etQuery);
	   	btnSearch = (Button) findViewById(R.id.btnSearch);

	 }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        case R.id.action_search:
            // search icon in action bar clicked; go to search activity
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;      
        }
        return super.onOptionsItemSelected(item);
	}
    
	
    public void onMovieSearch(View v) {
    	String movieName = etQuery.getText().toString();
    	Toast.makeText(this, "Searching for " + movieName, Toast.LENGTH_SHORT).show();

		View movieContainer = findViewById(R.id.movie_search_page);
		
		try {
			TomatoesAPIClient.getSearchMovies(URLEncoder.encode(movieName, "UTF-8"), new MovieSearchResponseHandler(this.getApplicationContext(), movieContainer));
		} catch (UnsupportedEncodingException e) {
			Log.d("DEBUG", "got exception");
			e.printStackTrace();
		}
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
