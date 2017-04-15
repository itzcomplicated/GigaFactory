package com.tca.gigafactory.tools;

/**
 * Created by TCA on 15-04-2017.
 */

public interface Logger {
    void logInfo(String info);
    void logDebug(String debugMessage);
    void logError(String errorMessage);
    void logError(String errorMessage,Throwable throwable);
}
