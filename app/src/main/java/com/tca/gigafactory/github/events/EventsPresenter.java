package com.tca.gigafactory.github.events;

import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.Logger;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TCA on 15-04-2017.
 */

public class EventsPresenter implements EventsContract.Presenter {


    private final GithubServices githubServices;
    private EventsContract.View view;
    private final Logger logger;

    Call<List<Event>> eventsCall;


    @Inject
    public EventsPresenter(GithubServices githubServices, EventsContract.View view, Logger logger){
        this.githubServices=githubServices;
        this.view=view;
        this.logger=logger;
    }


    @Override
    public void start() {
       loadEvents();
    }

    @Override
    public void stop() {

        this.view=null;
        if(null!=eventsCall){
            eventsCall.cancel();
        }

    }

    @Override
    public void loadEvents() {

        eventsCall=githubServices.listPublicEvents();

        view.showProgress();

        eventsCall.enqueue(new Callback<List<Event>>() {


            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.body()!=null){
                    logger.logInfo("Received response with " + response.body().size() + " elements");
                    view.hideProgress();
                    view.showEvents(response.body());
                }
                else {
                    logger.logInfo("Response is empty");
                    view.hideProgress();
                    view.loadingFailedMessage();
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                logger.logInfo("Some thing went wrong");
                view.hideProgress();
                view.loadingFailedMessage();

            }
        });
    }
}
