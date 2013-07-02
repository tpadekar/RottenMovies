package com.group5.rottenmovies;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.group5.rottenmovies.uielements.MovieImage;
import com.loopj.android.image.SmartImageView;

public class MovieImageArrayAdapter extends ArrayAdapter<MovieImage> {

	public MovieImageArrayAdapter(Context context, List<MovieImage> images) {
		super(context, R.layout.movie_image_item, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		MovieImage imageInfo = this.getItem(position);
		SmartImageView ivImage;

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflater.inflate(
					R.layout.movie_image_item, parent, false);

		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;

	}

}
