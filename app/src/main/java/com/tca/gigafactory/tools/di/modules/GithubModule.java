package com.tca.gigafactory.tools.di.modules;

import com.tca.gigafactory.github.api.GithubServices;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TCA on 16-04-2017.
 */

@Module(includes = {NetworkModule.class})
public class GithubModule {

    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public GithubServices providesGithubServices(Retrofit retrofit){
        return retrofit.create(GithubServices.class);
    }


}
