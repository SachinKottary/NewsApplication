package com.sachin.newsapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProviders;

import com.sachin.newsapplication.base.BaseActivity;
import com.sachin.newsapplication.base.ViewModelFactory;
import com.sachin.newsapplication.ui.main.MainFragment;
import com.sachin.newsapplication.ui.main.NewsViewModel;
import com.sachin.newsapplication.utils.FilterOption;
import com.sachin.newsapplication.utils.FragmentUtils;

import javax.inject.Inject;

import static com.sachin.newsapplication.utils.FragmentUtils.FRAGMENT_NEWS_LIST;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        NewsApplication.getApplication().getApplicationComponent().inject(this);
        newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel.class);
        if (savedInstanceState == null) {
            setCurrentFragment(null, FRAGMENT_NEWS_LIST, FRAG_ADD, R.id.container);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.popular:
            newsViewModel.filterNewList(FilterOption.POPULAR);
            return(true);
        case R.id.recent:
            newsViewModel.filterNewList(FilterOption.RECENT);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}
