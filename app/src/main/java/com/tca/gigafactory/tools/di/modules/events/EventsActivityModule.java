package com.tca.gigafactory.tools.di.modules.events;

import android.app.ProgressDialog;

import com.tca.gigafactory.R;
import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.events.EventsActivity;
import com.tca.gigafactory.github.events.EventsAdapter;
import com.tca.gigafactory.github.events.EventsContract;
import com.tca.gigafactory.github.events.EventsPresenter;
import com.tca.gigafactory.tools.ImageLoader;
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
        progressDialog.setTitle(eventsActivity.getString(R.string.loading_message));
        return progressDialog;
    }

    @Provides
    public EventsAdapter providesEventsAdapter(ImageLoader imageLoader, Logger logger){
        EventsAdapter eventsAdapter = new EventsAdapter(eventsActivity, imageLoader,logger);
        return eventsAdapter;
    }

    @Provides
    public EventsContract.Presenter providesEventsPresenter(GithubServices githubServices, Logger logger){
        EventsPresenter presenter= new EventsPresenter(githubServices,eventsActivity, logger);
        return presenter;
    }
}
