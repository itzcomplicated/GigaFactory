package com.tca.gigafactory.github.events;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tca.gigafactory.GigaApplication;
import com.tca.gigafactory.R;
import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;
import com.tca.gigafactory.tools.di.componets.events.DaggerEventsActivityComponent;
import com.tca.gigafactory.tools.di.componets.events.EventsActivityComponent;
import com.tca.gigafactory.tools.di.modules.events.EventsActivityModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsActivity extends AppCompatActivity  implements EventsContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list_view_events)
    ListView listViewEvents;

    @Inject
    ProgressDialog progressDialog;

    @Inject
    EventsAdapter eventsAdapter;

    @Inject
    EventsContract.Presenter presenter;

    @Inject
    GithubServices githubServices;

    @Inject
    ImageLoader imageLoader;

    @Inject
    Logger logger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        GigaApplication gigaApplication=(GigaApplication)getApplication();
        EventsActivityComponent component = DaggerEventsActivityComponent.builder()
                .eventsActivityModule(new EventsActivityModule(this))
                .gigaApplicationComponent(gigaApplication.getGigaApplicationComponent())
                .build();

        component.injectEventsActivity(this);
        listViewEvents.setAdapter(eventsAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showProgress() {
        if(progressDialog==null) {
            progressDialog.show();
        }

    }

    @Override
    public void hideProgress() {
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showEvents(List<Event> events) {
        eventsAdapter.addEvents(events);
    }

    @Override
    public void loadingFailedMessage() {
        Snackbar.make(listViewEvents, R.string.events_load_failed_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, null).show();
    }
}
