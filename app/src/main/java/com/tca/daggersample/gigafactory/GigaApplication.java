package com.tca.daggersample.gigafactory;

import android.app.Application;

import com.tca.daggersample.gigafactory.tools.timber.ReleaseTree;

import timber.log.Timber;

/**
 * Created by TCA on 13-04-2017.
 */

public class GigaApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
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
        Timber.d("Loading up Giga Factory");
        Timber.e("This in just for testing timber. NOT AN ERROR :)");
    }
}
