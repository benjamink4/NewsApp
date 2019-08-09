package com.example.android.newsapp;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    private String Url;

    public ArticleLoader(Context context, String Url) {
        super(context);
        this.Url = Url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public List<Article> loadInBackground() {

        if (Url.length() == 0 || Url == null) {
            return null;
        }

        ArrayList<Article> result = QueryUtils.fetchArticleData(Url);
        return result;
    }
}



