package com.sachin.newsapplication.utils;

import androidx.fragment.app.Fragment;

import com.sachin.newsapplication.ui.main.MainFragment;

public class FragmentUtils {

    public static final int FRAGMENT_NEWS_LIST = 0;

    /**
     * Returns the fragment name to be instantiate
     *
     * @param type int identifier to differentiate the fragment
     * @return string fragment name
     */
    public static String getFragmentTag(int type) {
        switch (type) {
            case FRAGMENT_NEWS_LIST:
                return MainFragment.class.getName();
        }
        return "";
    }

}