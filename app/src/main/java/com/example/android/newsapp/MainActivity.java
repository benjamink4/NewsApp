package com.example.android.newsapp;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {
    private String query;
    private ArticleAdapter adapter;
    private Button button;
    private EditText editText;
    private ListView listView;
    private ProgressBar progressBar;
    private static final int ARTICLE_LOADER_ID=1;
    private TextView emptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        listView=(ListView)findViewById(R.id.List);
        emptyState=(TextView)findViewById(R.id.emptyState);

        progressBar=(ProgressBar)findViewById(R.id.progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                query="https://content.guardianapis.com/search?q="+editText.getText().toString()+"&api-key=test";
                search();



            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article current_article=adapter.getItem(position);
                Uri articleUri=Uri.parse(current_article.getWebUrl());
                Intent web=new Intent(Intent.ACTION_VIEW,articleUri);
                startActivity(web);


            }
        });



    }
    public void search(){

        getLoaderManager().restartLoader(ARTICLE_LOADER_ID,null,MainActivity.this);
        progressBar.setVisibility(View.VISIBLE);
        emptyState.setVisibility(View.GONE);


        adapter=new ArticleAdapter(this, new ArrayList<Article>());
        listView.setAdapter(adapter);

    }



    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {



        // TODO: Create a new loader for the given URL
        emptyState.setText("");

        // Create a new loader for the given URL
        return new ArticleLoader(this,query);
    }

    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        //bookView.setEmptyView(blankState);


        // TODO: Update the UI with the result

        // TODO: Loader reset, so we can clear out our existing data.
        listView.setEmptyView(emptyState);
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.


        if (articles != null && !articles.isEmpty()) {


            adapter.clear();

            adapter.addAll(articles);
            progressBar.setVisibility(View.GONE);


        }
        else if(articles!=null && articles.isEmpty()){

            emptyState.setText("No Results found");
            progressBar.setVisibility(View.GONE);
        }

        else if(!isConnected()){
            emptyState.setText("No Internet Connection");
            progressBar.setVisibility(View.GONE);
        }


    }

    public boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean is_connected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return is_connected;



    }






    public void onLoaderReset(Loader<List<Article>> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.


        adapter.clear();

    }



}
