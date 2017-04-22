package com.tca.gigafactory.tools.di.modules;

import com.tca.gigafactory.tools.Logger;
import com.tca.gigafactory.tools.di.scope.ApplicationScope;
import com.tca.gigafactory.tools.timber.TimberLogger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TCA on 16-04-2017.
 */

@Module
public class LoggerModule {

    @Provides
    @ApplicationScope
    public Logger providesLogger(){
        return new TimberLogger();
    }
}
