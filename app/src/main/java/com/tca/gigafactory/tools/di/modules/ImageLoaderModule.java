package com.tca.gigafactory.tools.di.modules;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.picasso.PicassoImageLoader;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by TCA on 16-04-2017.
 */

@Module(includes = {ContextModule.class,NetworkModule.class})
public class ImageLoaderModule {

    @Provides
    public Picasso providesPicasso(Context context, OkHttpClient okHttpClient){
        Picasso picasso =new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
        return picasso;
    }

    @Provides
    public ImageLoader providesImageLoader(Picasso picasso){
        return new PicassoImageLoader(picasso);
    }
}
