package com.tca.gigafactory.tools.di.modules;

/**
 * Created by TCA on 16-04-2017.
 */

import android.content.Context;

import com.tca.gigafactory.tools.Logger;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = {ContextModule.class,JsonModule.class,LoggerModule.class})
public class NetworkModule {

    @Provides
    public HttpLoggingInterceptor.Logger providesHttpLoggingInterceptorLogger(final Logger logger){
        return new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                logger.logInfo(message);
            }
        };
    }

    @Provides
    public HttpLoggingInterceptor providesHttpLoggingInterceptor(HttpLoggingInterceptor.Logger logger){
        return new HttpLoggingInterceptor(logger);
    }


    @Provides
    public File providesFile(Context context){
        return  new File(context.getCacheDir(),"giga_network_cache");
    }

    @Provides
    public Cache providesCache(File fileToStoreCache)  {
        final int cacheSizeInMb=5*1024*1024;
        Cache cache=new Cache(fileToStoreCache,cacheSizeInMb);
        return cache;
    }

    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor interceptor,Cache cache){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

}
