package com.tca.gigafactory.github.events;

import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TCA on 23-04-2017.
 */
public class EventsPresenterTest {

    EventsPresenter eventsPresenter;
    GithubServices githubServices;
    EventsContract.View view;
    Logger logger;


    @Before
    public void setUp(){

        githubServices= mock(GithubServices.class);
        view= mock(EventsContract.View.class);
        logger = mock(Logger.class);

        eventsPresenter=new EventsPresenter(githubServices, view, logger);
    }

    @After
    public void tearDown(){
    }

    @Test
    public void sampleTest(){
        Assert.assertEquals(4,2+2);
    }

    @Test
    public void verifyLoadTriggersWebservice(){
        //Given
        mockCallForEvents();
        //When
        eventsPresenter.loadEvents();
        //Then
        verify(githubServices).listPublicEvents();
    }

    @Test
    public void verifyLoadTriggersWebserviceCalledOnlyOnce(){
        //Given
        mockCallForEvents();
        //When
        eventsPresenter.loadEvents();
        //Then
        verify(githubServices,times(1)).listPublicEvents();
    }

    private void mockCallForEvents() {
        Call<List<Event>> call= mock(Call.class);
        when(githubServices.listPublicEvents()).thenReturn(call);
    }


}