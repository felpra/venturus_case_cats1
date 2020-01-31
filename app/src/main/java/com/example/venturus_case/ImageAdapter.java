package com.example.venturus_case;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

/**
 * {@link ImageAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Imageg} objects.
 */
public class ImageAdapter extends ArrayAdapter<Imageg>  {

  public ImageAdapter(Context context, ArrayList<Imageg> places) {
    super(context, 0, places);
  }


  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    view = LayoutInflater.from(getContext()).inflate(R.layout.activity_gridview, null); // inflate the layout
    ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
     // set logo images


    Imageg currentImage = getItem(i);

    ImageView imageView =  view.findViewById(R.id.icon);

    Glide.with(getContext()).load(currentImage.getImageUrl()).into(imageView);


    return view;
  }
}

