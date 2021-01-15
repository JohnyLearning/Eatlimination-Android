package com.ihadzhi.eatlimination.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ihadzhi.eatlimination.network.SpoonacularService
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchFoodsViewModel(application: Application) : BaseViewModel(application) {

    private var foundFoods: MutableLiveData<List<SpoonFoodAuto>>? = null
    private val spoonacularService: SpoonacularService = SpoonacularService()

    @Throws(Exception::class)
    fun findFoods(searchFoodsQuery: CharSequence): LiveData<List<SpoonFoodAuto>> {
//        if (foundFoods == null) {
        foundFoods = MutableLiveData()
        searchFoods(searchFoodsQuery)
        //        }
        return foundFoods as MutableLiveData<List<SpoonFoodAuto>>
    }

    @Throws(Exception::class)
    private fun searchFoods(searchFoodsQuery: CharSequence) {
        foundFoods = MutableLiveData()
        spoonacularService.searchFoods(searchFoodsQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ foundFoodsFromResponse: List<SpoonFoodAuto> -> foundFoods?.setValue(foundFoodsFromResponse) }
                ) { throwable: Throwable? -> throw Exception(throwable) }
    }

}