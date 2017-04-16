package com.tca.gigafactory;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;
import com.tca.gigafactory.tools.di.DaggerGigaApplicationComponent;
import com.tca.gigafactory.tools.di.componets.GigaApplicationComponent;
import com.tca.gigafactory.tools.di.modules.ContextModule;

/**
 * Created by TCA on 13-04-2017.
 */

public class GigaApplication extends Application{

    private GithubServices githubServices;

    private ImageLoader imageLoader;

    private Logger logger;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this);
        }

        GigaApplicationComponent component= DaggerGigaApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        githubServices=component.getGithubServices();
        imageLoader=component.getImageLoader();
        logger=component.getLogger();
    }

    public GithubServices getGithubServices() {
        return githubServices;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public Logger getLogger() {
        return logger;
    }
}
