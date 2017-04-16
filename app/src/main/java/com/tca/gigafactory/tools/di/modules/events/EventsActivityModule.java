package com.tca.gigafactory.tools.di.modules.events;

import com.tca.gigafactory.github.events.EventsActivity;

import dagger.Provides;

/**
 * Created by TCA on 16-04-2017.
 */

public class EventsActivityModule {

    private final EventsActivity eventsActivity;

    public EventsActivityModule(EventsActivity eventsActivity) {
        this.eventsActivity = eventsActivity;
    }

    @Provides
    public EventsActivity providesEventsActivity() {
        return eventsActivity;
    }
}
