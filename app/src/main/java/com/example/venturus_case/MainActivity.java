package com.example.venturus_case;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private Button myButton;

  private ImageAdapter mAdapter;
  private static final String IMAGE_REQUEST_URL =
    "https://api.imgur.com/3/gallery/search/?q=cats";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    GridView imageGridView = (GridView) findViewById(R.id.simpleGridView);

    mAdapter = new ImageAdapter(this, new ArrayList<Imageg>());

    myButton=(Button)findViewById(R.id.mybtn);

    findViewById(R.id.mybtn).setVisibility(View.GONE);

    findViewById(R.id.error).setVisibility(View.GONE);




    imageGridView.setAdapter(mAdapter);

    // Start the AsyncTask to fetch the news data
    ImageAsyncTask task = new ImageAsyncTask();

    task.execute(IMAGE_REQUEST_URL);


  }

  public void onBtnClick() {
    myButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_LONG).show();
        Intent reload = getIntent();
        finish();
        startActivity(reload);

      }
    });
  }


    private class ImageAsyncTask extends AsyncTask<String, Void, List<Imageg>> {


      @Override
      protected List<Imageg> doInBackground(String... urls) {
        // Don't perform the request if there are no URLs, or the first URL is null
        if (urls.length < 1 || urls[0] == null) {
          return null;
        }
        Context c = getApplicationContext();
        List<Imageg> result = QueryUtils.fetchImageData(urls[0], c);

        return result;
      }


      @Override
      protected void onPostExecute(List<Imageg> data) {

        mAdapter.clear();


        if (data != null && !data.isEmpty()) {
          mAdapter.addAll(data);
        } else {
          findViewById(R.id.mybtn).setVisibility(View.VISIBLE);
          findViewById(R.id.error).setVisibility(View.VISIBLE);


          onBtnClick();

        }
        findViewById(R.id.loading_indicator).setVisibility(View.GONE);
      }
    }





}
