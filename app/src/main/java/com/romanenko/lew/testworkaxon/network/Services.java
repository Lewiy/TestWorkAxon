package com.romanenko.lew.testworkaxon.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {

    private static final OkHttpClient.Builder CLIENT = new OkHttpClient
            .Builder();
    private static String BASE_URL = "https://randomuser.me";

    private static final int TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 120;
    private static final int CONNECT_TIMEOUT = 10;


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()

                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    };

    static {
        CLIENT.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.readTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    public static <S> S createService(Class<S> serviceClass) {
        CLIENT.interceptors().add(interceptor);
        Retrofit retrofit = builder.client(CLIENT.build()).build();
        return retrofit.create(serviceClass);
    }
}
