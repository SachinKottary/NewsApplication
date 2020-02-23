package com.sachin.newsapplication.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sachin.newsapplication.NewsApplication;
import com.sachin.newsapplication.R;
import com.sachin.newsapplication.base.BaseFragment;
import com.sachin.newsapplication.base.ViewModelFactory;
import com.sachin.newsapplication.databinding.MainFragmentBinding;
import com.sachin.newsapplication.ui.main.adapter.NewsRepositoryAdapter;

import javax.inject.Inject;

public class MainFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;
    private NewsViewModel viewModel;
    private NewsRepositoryAdapter newsRepositoryAdapter;
    private MainFragmentBinding binding;

    private ImageView networkErrorImage;
    private Button retryButton;
    private ProgressBar progressBar;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        NewsApplication.getApplication().getApplicationComponent().inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        newsRepositoryAdapter = new NewsRepositoryAdapter();
        networkErrorImage = binding.networkErrorImage;
        retryButton = binding.retryButton;
        progressBar = binding.progressBar;
        binding.gitRepoRecyclerView.setAdapter(newsRepositoryAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() == null) return;
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(NewsViewModel.class);
        binding.setNewsViewModel(viewModel);
        observeGitTopRepoDetails();
    }

    private void observeGitTopRepoDetails() {
        viewModel.newsListLiveData.observe(this, detailsList -> {
            if (newsRepositoryAdapter == null || progressBar == null) return;
            progressBar.setVisibility(View.GONE);
            newsRepositoryAdapter.setNewsItemDetailList(detailsList);
        });

        viewModel.errorLiveData.observe(this, detailsList -> {
            if (newsRepositoryAdapter.getItemCount() <= 0) {
                onNetworkDisConnected();
            }
            if (progressBar == null) return;
            progressBar.setVisibility(View.GONE);
        });
    }

    private void downloadNewsData() {
        viewModel.downloadNewsDataFromServer();
    }

    @Override
    public boolean handleNetworkState() {
        return true;
    }

    @Override
    public void onNetworkDisConnected() {
        if (newsRepositoryAdapter == null) return;
        int networkErrorVisibility = newsRepositoryAdapter.getItemCount() <= 0 ? View.VISIBLE : View.GONE;
        networkErrorImage.setVisibility(networkErrorVisibility);
        retryButton.setVisibility(networkErrorVisibility);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onNetworkConnected() {
        if (newsRepositoryAdapter == null || newsRepositoryAdapter.getItemCount() > 0) return;
        networkErrorImage.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        downloadNewsData();
    }

}

