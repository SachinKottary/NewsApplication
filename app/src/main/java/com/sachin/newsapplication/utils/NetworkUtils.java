package com.sachin.newsapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import static com.sachin.newsapplication.network.ServerConstants.END_POINT_CAROUSELL_NEWS;
import static com.sachin.newsapplication.network.ServerConstants.REQUEST_GET_NES_LIST;


public class NetworkUtils {
    /**
     * checks if internet is present or not
     *
     * @param context To get the connectivity info object
     * @return boolean true if internet present, else false
     */
    public static boolean isInternetPresent(Context context) {
        if (context == null) return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities == null) return false;
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
        } else {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *  Returns the URL endpoint for particular request
     * @param requestType int to identify the endpoint
     * @return string endpoint for the request
     */
    public static String getUrl(int requestType) {
        switch (requestType) {
            case REQUEST_GET_NES_LIST:
                return END_POINT_CAROUSELL_NEWS;
        }
        return "";
    }

}
