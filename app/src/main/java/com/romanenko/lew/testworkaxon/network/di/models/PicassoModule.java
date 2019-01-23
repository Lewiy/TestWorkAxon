package com.romanenko.lew.testworkaxon.network.di.models;

import android.content.Context;

import com.romanenko.lew.testworkaxon.network.di.scopes.ApplicationScope;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module (includes = OkHttpClientModule.class)
public class PicassoModule {

    @ApplicationScope
    @Provides
    public Picasso picasso( Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader).
                build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }
}
