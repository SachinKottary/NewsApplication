package com.sachin.newsapplication;

import android.app.Fragment;

import com.sachin.newsapplication.ui.main.MainFragment;
import com.sachin.newsapplication.utils.FragmentUtils;

import org.junit.Assert;
import org.junit.Test;

import static com.sachin.newsapplication.utils.FragmentUtils.FRAGMENT_NEWS_LIST;

public class FragmentUtilsTest {

    @Test
    public void checkIfFragmentNameIsEmptyWhenParamIsInvalid() {
        Assert.assertEquals("", FragmentUtils.getFragmentTag(100));
    }

    @Test
    public void checkIfFragmentNameIsValid() {
        Assert.assertEquals(MainFragment.class.getName(), FragmentUtils.getFragmentTag(FRAGMENT_NEWS_LIST));
    }
}
