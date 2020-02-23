package com.sachin.newsapplication;

import com.sachin.newsapplication.utils.DateAndTimeUtils;

import org.junit.Assert;
import org.junit.Test;

public class DateAndTimeUtilsTest {

    @Test
    public void checkIfTheDifferenceInSeconds() {
        long oldTime = System.currentTimeMillis() - 10;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.SECONDS));
    }

    @Test
    public void checkIfTheDifferenceInMinutes() {
        long oldTime = System.currentTimeMillis() - 130554;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.MINUTES));
    }


    @Test
    public void checkIfTheDifferenceInHours() {
        long oldTime = System.currentTimeMillis() - 3600000;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.HOURS));
    }

    @Test
    public void checkIfTheDifferenceInDays() {
        long oldTime = System.currentTimeMillis() - 93600000;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.DAYS));
    }

    @Test
    public void checkIfTheDifferenceInWeeks() {
        long oldTime = System.currentTimeMillis() - 720000000;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.WEEKS));
    }

    @Test
    public void checkIfTheDifferenceInMonths() {
        long oldTime = System.currentTimeMillis() - 2790000000L;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.MONTHS));
    }

    @Test
    public void checkIfTheDifferenceInYears() {
        long oldTime = System.currentTimeMillis() - 33480000000L;
        Assert.assertTrue(DateAndTimeUtils.getDate(oldTime).contains(DateAndTimeUtils.YEARS));
    }

}