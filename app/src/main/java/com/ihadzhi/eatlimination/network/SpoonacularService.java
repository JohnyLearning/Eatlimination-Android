package com.ihadzhi.eatlimination.network;

import com.ihadzhi.eatlimination.BuildConfig;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpoonacularService {

    /**
     * To prospective reviewers: please replace the value of the API_KEY with your own Movie DB api key
     */
    private static final String SPOONACULAR_API_KEY = BuildConfig.SPOONACULAR_API_KEY;

    private SpoonacularApi spoonacularApi;

    // reference: https://futurestud.io/tutorials/retrofit-2-how-to-add-query-parameters-to-every-request
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apiKey", SPOONACULAR_API_KEY)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            })
            .build();

    public SpoonacularService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        spoonacularApi = retrofit.create(SpoonacularApi.class);
    }

    public Single<List<SpoonFoodAuto>> searchFoods(CharSequence searchFoodQuery) {
        return spoonacularApi.searchAutoComplete(searchFoodQuery, 20);
    }
}
