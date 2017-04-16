package com.tca.gigafactory.github.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tca.gigafactory.R;
import com.tca.gigafactory.github.api.models.Event;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TCA on 15-04-2017.
 */
@SuppressLint("ViewConstructor")
public class EventListItem extends FrameLayout {

    private final Context context;
    private final ImageLoader imageLoader;
    private final Logger logger;

    @BindView(R.id.imageViewAvatar)
    ImageView imageViewAvatar;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.textViewContent)
    TextView textViewContent;


    public EventListItem(Context context, ImageLoader imageLoader, Logger logger){
        super(context);
        this.context=context;
        this.imageLoader=imageLoader;
        this.logger=logger;
        inflate(getContext(), R.layout.list_item_event, this);
        ButterKnife.bind(this);
    }

    public void loadEvent(Event event){
        imageLoader.loadImage(event.getActor().getAvatarUrl(),imageViewAvatar);
        textViewTitle.setText(event.getType());
        textViewContent.setText(event.getId() + " " + event.getCreatedAt());
    }
}
