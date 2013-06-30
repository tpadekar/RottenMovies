/**
 * 
 */
package com.group5.rottenmovies.uielements;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.group5.rottenmovies.R;

/**
 * @author padekar
 *
 */
public class MovieCastView {

	private Context context;
	private MovieCastMember actor;
	private static LayoutInflater inflater=null;
	
	public MovieCastView(Context context, MovieCastMember castMember) {
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.actor = castMember;
	}
	
	
	public View getView() {
		View v = inflater.inflate(R.layout.movie_cast_item, null);
		
		if(actor != null) {
			TextView tt = (TextView) v.findViewById(R.id.toptext);
			TextView bt = (TextView) v.findViewById(R.id.bottomtext);
			if(tt != null) {
				tt.setText(actor.getActorName());
			}
			if(bt != null) {
				bt.setText(actor.getCharacter());
			}
		}
		
		return v;
	}
	
	

}
