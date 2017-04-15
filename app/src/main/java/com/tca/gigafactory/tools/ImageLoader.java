package com.tca.gigafactory.tools;

import android.widget.ImageView;

/**
 * Created by TCA on 15-04-2017.
 */

public interface ImageLoader {
    void loadImage(String url, ImageView imageView);
    void loadImage(String url, ImageView imageView,int placeHolder);
}
