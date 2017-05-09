package com.tca.gigafactory.tools.di.modules.events;

import android.app.ProgressDialog;
import android.content.Context;

import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.events.EventsActivity;
import com.tca.gigafactory.github.events.EventsContract;
import com.tca.gigafactory.github.events.EventsPresenter;
import com.tca.gigafactory.tools.Logger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TCA on 16-04-2017.
 */

@Module
public class EventsActivityModule {

    private final EventsActivity eventsActivity;

    public EventsActivityModule(EventsActivity eventsActivity) {
        this.eventsActivity = eventsActivity;
    }


    @Provides
    public ProgressDialog providesProgressDialog(){
        ProgressDialog progressDialog = new ProgressDialog(eventsActivity);
        return progressDialog;
    }

    @Provides
    public Context providesContext(){
        return eventsActivity;
    }

    @Provides
    public EventsContract.Presenter providesEventsPresenter(GithubServices githubServices, Logger logger){
        EventsPresenter presenter= new EventsPresenter(githubServices, eventsActivity, logger);
        return presenter;
    }
}
