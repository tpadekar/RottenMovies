package com.group5.rottenmovies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import com.group5.rottenmovies.uielements.MovieCardAdapter;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings;
import com.novoda.imageloader.core.LoaderSettings.SettingsBuilder;
import com.novoda.imageloader.core.cache.LruBitmapCache;

public class HomeActivity extends Activity {
	
	private JSONObject inTheaters;
	public static ImageManager imageManager;
	
	public static final ImageManager getImageManager() {
	    return imageManager;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        int PERCENTAGE_OF_CACHE = 50;
        LoaderSettings settings = new SettingsBuilder().withCacheManager(new LruBitmapCache(this, PERCENTAGE_OF_CACHE)).withDisconnectOnEveryCall(true).build(this);
        imageManager = new ImageManager(this, settings);
        
        // Use the AssetManager for the temporary files stored in assets...
        try {
        	AssetManager am = getAssets();
        	this.inTheaters = new JSONObject(getStringFromFile(am, "intheaters.json"));
        	
        	ArrayList<JSONObject> list = new ArrayList<JSONObject>();     
        	JSONArray jsonArray = this.inTheaters.getJSONArray("movies"); 
        	if (jsonArray != null) { 
        	   int len = jsonArray.length();
        	   for (int i=0;i<len;i++){ 
        	    list.add(jsonArray.getJSONObject(i));
        	   } 
        	} 
        	
        	MovieCardAdapter mc = new MovieCardAdapter(this, list);
        	
        	LinearLayout inTheater = (LinearLayout) findViewById(R.id.intheater_carousel);
        	for(int i=0; i < mc.getCount(); i++) {
        		inTheater.addView(mc.getView(i, null, inTheater));
        	}
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
          sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public static String getStringFromFile (AssetManager am, String filePath) throws Exception {
        InputStream is = am.open(filePath);
        String ret = convertStreamToString(is);
        //Make sure you close all streams.
        is.close();        
        return ret;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
}
