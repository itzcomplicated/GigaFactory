package com.tca.gigafactory.github.api;

import com.tca.gigafactory.github.api.models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by TCA on 14-04-2017.
 */

public interface GithubServices {
        @GET("events")
        Call<List<Event>> listPublicEvents();
}

