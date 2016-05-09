package com.onthespot.vikaskumar.filterlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] bookName;
    String[] bookAuthor;
    String[] publisher;
    int[] coverImage;
    ArrayList<BookCollection> arraylist = new ArrayList<BookCollection>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bookName = new String[]{"Half Girlfriend", "Harray Potter", "Da Vinchi Code", "Divergent",
                "The Hunger Games", "Angels & Demons", "Nemesis", "The Grand Design", "Gone Girl", "The Maze Runner"};

        bookAuthor = new String[]{"Chetan Bhagat", "J.K Rowling", "Dan Brown",
                "Veronica Roth", "Suzanne Collins", "Dan Brown", " Agatha Christie",
                "Stephen Hawking", "Gillian Flynn","James Dashner"};

        publisher = new String[]{"Rupa", "Bloomsbury",
                "Doubleday Transworld", "HarperCollins","Scholastic Corporation", "Pocket Books", "Collins Crime Club",
                "Bantam Books", "Crown Publishing Group", "Dell Publishing"};

        coverImage = new int[]{R.drawable.half_girlfriend, R.drawable.harry
                        ,R.drawable.da_vinchi_code,R.drawable.divergent,R.drawable.hungergames,R.drawable.angelsanddemons
                        ,R.drawable.agatha,R.drawable.grand_design,R.drawable.gone_girl,R.drawable.maze_runner};


        list = (ListView) findViewById(R.id.listview);


        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // used to hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return false;
            }
        });


        for (int i = 0; i < bookName.length; i++) {
            BookCollection wp = new BookCollection(bookName[i], bookAuthor[i],
                    publisher[i],coverImage[i]);
            arraylist.add(wp);
        }


        adapter = new ListViewAdapter(this, arraylist);
        list.setAdapter(adapter);
        editsearch = (EditText) findViewById(R.id.search);
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(arg0.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
