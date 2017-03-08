package com.coffeearmy.marvelheroes.injector.module;

import android.app.Application;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.data.net.ComicInterceptor;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;
import com.coffeearmy.marvelheroes.navigation.Navigator;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * :3
 */
@Module
public class ApplicationModule {

    Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @Named("public_key")
    String providePublicKey() {
        return mApplication.getString(R.string.public_key);
    }

    @Provides
    @Singleton
    @Named("private_key")
    String providePrivateKey() {
        return mApplication.getString(R.string.private_key);
    }

    @Provides
    @Singleton
    @Named("base_url")
    String provideBaseUrl() {
        return "http://gateway.marvel.com/v1/public/";
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(@Named("private_key") String privateKey, @Named("public_key") String publicKey) {
        return new ComicInterceptor(privateKey, publicKey);
    }

    @Provides
    @Singleton
    @Named("http_client_interceptor")
    OkHttpClient provideHttpClient(Interceptor interceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }


    @Provides
    @Singleton
    ComicsGateway provideComicsGateway(@Named("http_client_interceptor") OkHttpClient httpClient, @Named("base_url") String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(ComicsGateway.class);
    }

    @Provides
    @Singleton
    Navigator provideNavigator(){
        return new Navigator();
    }

}
