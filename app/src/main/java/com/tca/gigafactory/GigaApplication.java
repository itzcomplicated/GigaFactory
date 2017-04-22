package com.tca.gigafactory;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.tools.di.componets.DaggerGigaApplicationComponent;
import com.tca.gigafactory.tools.di.componets.GigaApplicationComponent;
import com.tca.gigafactory.tools.di.modules.ContextModule;

/**
 * Created by TCA on 13-04-2017.
 */

public class GigaApplication extends Application{

    GigaApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        component = DaggerGigaApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        GithubServices githubServices=component.getGithubServices();
        Log.d("GithubServices 1",githubServices.toString());

        GithubServices githubServices2=component.getGithubServices();
        Log.d("GithubServices 2",githubServices2.toString());

        GithubServices githubServices3=component.getGithubServices();
        Log.d("GithubServices 3",githubServices3.toString());
    }

    public GigaApplicationComponent getGigaApplicationComponent() {
        return component;
    }
}
