package com.tca.gigafactory.github;

import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.api.models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by TCA on 15-04-2017.
 */

public class EventsPresenter implements EventsContract.Presenter {


    private final GithubServices githubServices;
    private final EventsContract.View view;
    private final Timber timber;


    public EventsPresenter(GithubServices githubServices, EventsContract.View view, Timber timber){
        this.githubServices=githubServices;
        this.view=view;
        this.timber=timber;
    }


    @Override
    public void start() {
       loadEvents();
    }

    @Override
    public void loadEvents() {

        Call<List<Event>> eventsCall=githubServices.listPublicEvents();

        view.showProgress();

        eventsCall.enqueue(new Callback<List<Event>>() {


            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.body()!=null){
                    timber.i("Received response with " + response.body().size() + " elements");
                    view.hideProgress();
                    view.showEvents(response.body());
                }
                else {
                    timber.i("Response is empty");
                    view.hideProgress();
                    view.loadingFailedMessage();
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                timber.i("Some thing went wrong");
                view.hideProgress();
                view.loadingFailedMessage();

            }
        });
    }
}
