package com.onthespot.vikaskumar.filterlistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Vikas Kumar on 04-05-2016.
 */
public class SingleItemView extends Activity {
    // Declare Variables
    TextView txtrank;
    TextView txtcountry;
    TextView txtpopulation;
    String rank;
    String country;
    String population;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of bookName
        rank = i.getStringExtra("bookName");
        // Get the results of bookAuthor
        country = i.getStringExtra("bookAuthor");
        // Get the results of publisher
        population = i.getStringExtra("publisher");

        // Locate the TextViews in singleitemview.xml
        txtrank = (TextView) findViewById(R.id.name);
        txtcountry = (TextView) findViewById(R.id.author);
        txtpopulation = (TextView) findViewById(R.id.publisher);

        // Load the results into the TextViews
        txtrank.setText(rank);
        txtcountry.setText(country);
        txtpopulation.setText(population);
    }
}
