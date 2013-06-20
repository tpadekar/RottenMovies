package com.group5.rottenmovies;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class TomatoesAPIClient {
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static void makeBackendCall(String section, HashMap<String, String> parameters, AsyncHttpResponseHandler responseHandler) {
		StringBuffer path = new StringBuffer(AppConfiguration.SECTION_URL.get(section));
		
		if(parameters != null) {
			Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				path.append(entry.getKey()+"="+entry.getValue());
			}
		}
		Log.d("DEBUG", "Making a backend call for "+section+" : "+path.toString());
		client.get(path.toString(), responseHandler);
	}

}
