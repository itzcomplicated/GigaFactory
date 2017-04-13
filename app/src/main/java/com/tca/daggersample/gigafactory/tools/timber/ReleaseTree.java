package com.tca.daggersample.gigafactory.tools.timber;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by TCA on 13-04-2017.
 */

public class ReleaseTree extends Timber.Tree {

    @Override
    protected boolean isLoggable(String tag, int priority) {
        if(priority> Log.INFO)
            return super.isLoggable(tag, priority);
        else
            return false;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        /*************************************************
         * You can do what ever tou want with the log.   *
         * Like Uploading to another tool like fire base *
         * to monitor production issues.                 *
         *************************************************/

       Log.e(tag,message);
    }
}



