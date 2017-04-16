package com.tca.gigafactory.tools.di.modules;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TCA on 16-04-2017.
 */

@Module
public class GithubModule {

    @Provides
    String providesBaseUrl(){
        return "https://api.github.com/";
    }

}
