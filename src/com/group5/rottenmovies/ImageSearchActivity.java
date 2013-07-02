package com.group5.rottenmovies;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.group5.rottenmovies.uielements.MovieImage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearchActivity extends Activity {

	GridView gvResults;

	int page = 1;
	int pageSize = 24;

	ArrayList<MovieImage> imageResults = new ArrayList<MovieImage>();
	MovieImageArrayAdapter imageAdapter;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
		
		setupViews();
		imageAdapter = new MovieImageArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);

		// Search for movie using intent
		String movieName = getIntent().getStringExtra("movie_name");
		if (movieName != null) {
			getImages(movieName, 0, pageSize);
			gvResults.setTag(movieName);
		}

		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View parent,
					int position, long rowId) {
				Intent i = new Intent(getApplicationContext(),
						ImageDisplayActivity.class);
				MovieImage imageResult = imageResults.get(position);
				i.putExtra("url", imageResult.getFullUrl());
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_search, menu);
		return true;
	}

	private void setupViews() {

		gvResults = (GridView) findViewById(R.id.gvResults);
	}

	public void onShowMore(View v) {
		String query = (String) gvResults.getTag();
		// Toast.makeText(this, "More images for " + query, Toast.LENGTH_LONG)
		// .show();
		page = page + 1;
		getImages(query, (page-1)*pageSize, page*pageSize);
		gvResults.setSelection(page * pageSize);

	}

	private void getImages(String query, int startNum, int numResults) {
		AsyncHttpClient client = new AsyncHttpClient();

		client.get(
				"http://images.search.yahoo.com/images/view?o=js&native=1&b="
						+ startNum + "&vm=r&n=" + numResults + "&p="
						+ Uri.encode(query), new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						JSONArray imageJsonResults = null;

						try {
							imageJsonResults = response.getJSONArray("results");
							imageResults.clear();
							imageAdapter.clear();
							imageAdapter.addAll(MovieImage
									.fromJSONArray(imageJsonResults));

							Log.d("DEBUG", imageResults.toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
	}

}
