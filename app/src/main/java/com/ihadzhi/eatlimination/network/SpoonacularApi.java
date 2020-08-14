package com.ihadzhi.eatlimination.network;

import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface SpoonacularApi {

    @GET("food/ingredients/autocomplete")
    Single<List<SpoonFoodAuto>> searchAutoComplete(@Query("query") CharSequence searchString,
                                                   @Query("number") int numberOfResults,
                                                   @Query("metaInformation") Boolean includeMeta);

}
