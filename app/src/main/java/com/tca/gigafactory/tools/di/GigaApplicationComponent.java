package com.tca.gigafactory.tools.di;

import com.tca.gigafactory.github.api.GithubServices;
import com.tca.gigafactory.tools.ImageLoader;
import com.tca.gigafactory.tools.Logger;

import dagger.Component;

/**
 * Created by TCA on 16-04-2017.
 */

@Component
public interface GigaApplicationComponent {

    GithubServices getGithubServices();

    Logger getLogger();

    ImageLoader getImageLoader();

}
