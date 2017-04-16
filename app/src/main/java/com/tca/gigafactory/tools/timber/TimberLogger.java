package com.tca.gigafactory.tools.timber;

import com.tca.gigafactory.BuildConfig;
import com.tca.gigafactory.tools.Logger;

import timber.log.Timber;

/**
 * Created by TCA on 15-04-2017.
 */

public class TimberLogger implements Logger {

    public TimberLogger(){
        if(BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)+ "::" + element.getMethodName()+ "::" + element.getLineNumber();
                }
            });
        }
        else
        {
            Timber.plant(new ReleaseTree());
        }
    }

    @Override
    public void logInfo(String info) {
        Timber.i(info);
    }

    @Override
    public void logDebug(String debugMessage) {
        Timber.d(debugMessage);
    }

    @Override
    public void logError(String errorMessage) {
        Timber.e(errorMessage);
    }

    @Override
    public void logError(String errorMessage, Throwable throwable) {
        Timber.e(throwable,errorMessage);
    }
}
