package com.tca.gigafactory.tools.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TCA on 16-04-2017.
 */

@Module
public class JsonModule {

    @Provides
    public GsonBuilder providesGsonBuilder(){
        return new GsonBuilder();
    }

    @Provides
    public Gson providesGson(GsonBuilder gsonBuilder){
        Gson gson=gsonBuilder.create();
        return gson;
    }

    @Provides
    public GsonConverterFactory providesGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }
}
