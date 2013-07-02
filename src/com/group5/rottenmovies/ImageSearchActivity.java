package com.group5.rottenmovies;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

	EditText etQuery;
	GridView gvResults;
	Button btnSearch;

	int page = 0;
	int pageSize = 12;

	ArrayList<MovieImage> imageResults = new ArrayList<MovieImage>();
	MovieImageArrayAdapter imageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		setupViews();
		imageAdapter = new MovieImageArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);

		// Search for movie using intent
		String movieName = getIntent().getStringExtra("movie_name");
		if (movieName != null) {

			etQuery.setText(movieName);
			getImages(movieName, 0, pageSize);
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

		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);

	}

	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		// Toast.makeText(this, "Searching for " + query,
		// Toast.LENGTH_LONG).show();

		getImages(query, 0, pageSize);

	}

	public void onShowMore(View v) {
		String query = etQuery.getText().toString();
		// Toast.makeText(this, "More images for " + query, Toast.LENGTH_LONG)
		// .show();
		page = page + 1;
		getImages(query, 0, pageSize * (page + 1));
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
