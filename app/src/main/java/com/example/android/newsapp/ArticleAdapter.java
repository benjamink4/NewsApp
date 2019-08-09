package com.example.android.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    private List<Article>articles;

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context,0);
        this.articles=articles;



    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Article current_article=getItem(position);
        TextView title=(TextView)listItemView.findViewById(R.id.Title);
        title.setText(current_article.getTitle());

        TextView section=(TextView)listItemView.findViewById(R.id.Section);
        section.setText(current_article.getSection());




        TextView date=(TextView)listItemView.findViewById(R.id.date);
        Log.v("current date",current_article.getDate());
        /*
        Date dateObject=new Date(current_article.getDate());

        String formated=formatDate(dateObject);
        */

        date.setText(current_article.getDate());






        return listItemView;

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }



}
