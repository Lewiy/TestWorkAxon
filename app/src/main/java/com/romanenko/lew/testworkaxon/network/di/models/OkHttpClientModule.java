package com.romanenko.lew.testworkaxon.network.di.models;

import android.content.Context;

import com.romanenko.lew.testworkaxon.dI.models.ContextModule;
import com.romanenko.lew.testworkaxon.network.di.scopes.ApplicationScope;

import java.io.File;
import java.io.IOException;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Module (includes = ContextModule.class)
public class OkHttpClientModule {
    @ApplicationScope
    @Provides
    public OkHttpClient okHttpClient(Cache cache, Interceptor Interceptor){
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(Interceptor)
                .build();
    }

    @Provides
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    public File file( Context context){
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @Provides
    public Interceptor httpLoggingInterceptor(){
       return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()

                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
    }
}
