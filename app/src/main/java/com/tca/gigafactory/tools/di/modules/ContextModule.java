package com.tca.gigafactory.tools.di.modules;

import android.content.Context;

import com.tca.gigafactory.tools.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TCA on 16-04-2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    public Context context() {
        return context;
    }
}
