package com.tca.gigafactory.tools.di.componets;

import com.tca.gigafactory.github.events.EventsActivity;
import com.tca.gigafactory.tools.di.modules.events.EventsActivityModule;
import com.tca.gigafactory.tools.di.scope.EventScope;

import dagger.Component;

/**
 * Created by TCA on 16-04-2017.
 */
@EventScope
@Component(modules = EventsActivityModule.class, dependencies = GigaApplicationComponent.class)
public interface EventsActivityComponent {
    void injectEventsActivity(EventsActivity eventsActivity);
}
