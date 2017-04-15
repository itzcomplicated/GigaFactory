package com.tca.gigafactory.tools.picasso;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tca.gigafactory.tools.ImageLoader;

/**
 * Created by TCA on 15-04-2017.
 */

public class PicassoImageLoader implements ImageLoader {

    private final Picasso picasso;

    public PicassoImageLoader(Picasso picasso){
        this.picasso=picasso;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        picasso.load(url)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeHolder) {
        picasso.load(url)
                .placeholder(placeHolder)
                .into(imageView);
    }
}
