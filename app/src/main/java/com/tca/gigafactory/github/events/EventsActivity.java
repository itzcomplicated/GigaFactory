package com.tca.gigafactory.github.events;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.tca.gigafactory.R;
import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;
import com.tca.gigafactory.tools.picasso.PicassoImageLoader;
import com.tca.gigafactory.tools.timber.TimberLogger;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsActivity extends AppCompatActivity  implements EventsContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list_view_events)
    ListView listViewEvents;

    ProgressDialog progressDialog;

    EventsAdapter eventsAdapter;

    EventsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final Logger logger=new TimberLogger();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                logger.logInfo(message);
            }
        });

        File fileToCache= new File(getCacheDir(),"giga_network_cache");
        final int cacheSizeInMb=5*1024*1024;
        Cache cache=new Cache(fileToCache,cacheSizeInMb);

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();

        final Picasso picasso =new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        final ImageLoader imageLoader=new PicassoImageLoader(picasso);


        eventsAdapter = new EventsAdapter(this, imageLoader,logger);
        listViewEvents.setAdapter(eventsAdapter);


        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();
        GsonConverterFactory gsonConverterFactory=GsonConverterFactory.create(gson);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();

        GithubServices githubServices = retrofit.create(GithubServices.class);

        presenter= new EventsPresenter(githubServices,this, logger);


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
        presenter=null;
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
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getString(R.string.loading_message));
        }
        progressDialog.show();
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