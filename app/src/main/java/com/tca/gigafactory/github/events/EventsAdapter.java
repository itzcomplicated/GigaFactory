package com.tca.gigafactory.github.events;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by TCA on 15-04-2017.
 */

public class EventsAdapter extends BaseAdapter {


    private List<Event> events=new ArrayList<>();
    private final Context context;
    private final ImageLoader imageLoader;
    private final Logger logger;

    @Inject
    public EventsAdapter(Context context, ImageLoader imageLoader, Logger logger){
        this.context=context;
        this.imageLoader=imageLoader;
        this.logger=logger;
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
            eventListItem = new EventListItem(context, imageLoader, logger);
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
