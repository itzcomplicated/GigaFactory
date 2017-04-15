package com.tca.gigafactory.github;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tca.gigafactory.R;
import com.tca.gigafactory.github.api.models.Event;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by TCA on 15-04-2017.
 */
@SuppressLint("ViewConstructor")
public class EventListItem extends FrameLayout {

    private final Context context;
    private final Picasso picasso;
    private final Timber timber;

    @BindView(R.id.imageViewAvatar)
    ImageView imageViewAvatar;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.textViewContent)
    TextView textViewContent;


    public EventListItem(Context context, Picasso picasso, Timber timber){
        super(context);
        this.context=context;
        this.picasso=picasso;
        this.timber=timber;
        inflate(getContext(), R.layout.list_item_event, this);
        ButterKnife.bind(this);
    }

    public void loadEvent(Event event){
        textViewTitle.setText(event.getType());
        textViewContent.setText(event.getId() + " " + event.getCreatedAt());
    }
}
