package com.tca.gigafactory.github;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;
import com.tca.gigafactory.github.api.models.Event;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by TCA on 15-04-2017.
 */

public class EventsAdapter extends BaseAdapter {


    private List<Event> events=new ArrayList<>();
    private final Context context;
    private final Picasso picasso;
    private final Timber timber;

    public EventsAdapter(Context context, Picasso picasso, Timber timber){
        this.context=context;
        this.picasso=picasso;
        this.timber=timber;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(events.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventListItem eventListItem;
        if(convertView == null) {
            eventListItem = new EventListItem(context, picasso, timber);
        } else {
            eventListItem = EventListItem.class.cast(convertView);
        }

        eventListItem.loadEvent(events.get(position));

        return eventListItem;
    }


    public void addEvents(List<Event> events){
        if(events!=null) {
            this.events.addAll(events);
        }
        notifyDataSetChanged();
    }
}
