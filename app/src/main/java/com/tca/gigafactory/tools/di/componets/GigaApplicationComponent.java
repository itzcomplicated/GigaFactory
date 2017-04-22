package com.tca.gigafactory.tools.di.componets;

import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;
import com.tca.gigafactory.tools.di.modules.GithubModule;
import com.tca.gigafactory.tools.di.modules.ImageLoaderModule;
import com.tca.gigafactory.tools.di.modules.LoggerModule;
import com.tca.gigafactory.tools.di.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by TCA on 16-04-2017.
 */

@ApplicationScope
@Component(modules = {GithubModule.class, ImageLoaderModule.class, LoggerModule.class})
public interface GigaApplicationComponent {

    GithubServices getGithubServices();

    Logger getLogger();

    ImageLoader getImageLoader();

}
