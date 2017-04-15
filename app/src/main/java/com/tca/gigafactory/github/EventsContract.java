package com.tca.gigafactory.github;

import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.mvp.BasePresenter;
import com.tca.gigafactory.tools.mvp.BaseView;

import java.util.List;

/**
 * Created by TCA on 15-04-2017.
 */

public interface EventsContract {

    interface View extends BaseView<Presenter>{

        void showEvents(List<Event> events);

        void loadingFailedMessage();


    }
    interface Presenter extends BasePresenter{

        void loadEvents();

    }
}
