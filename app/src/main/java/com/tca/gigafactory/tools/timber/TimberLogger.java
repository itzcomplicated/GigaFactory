package com.tca.gigafactory.tools.timber;

import com.tca.gigafactory.tools.Logger;

import timber.log.Timber;

/**
 * Created by TCA on 15-04-2017.
 */

public class TimberLogger implements Logger {

    @Override
    public void logInfo(String info) {
        Timber.i(info);
    }

    @Override
    public void logDebug(String debugMessage) {
        Timber.d(debugMessage);
    }

    @Override
    public void logError(String errorMessage) {
        Timber.e(errorMessage);
    }

    @Override
    public void logError(String errorMessage, Throwable throwable) {
        Timber.e(throwable,errorMessage);
    }
}
