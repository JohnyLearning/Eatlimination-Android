package com.ihadzhi.eatlimination.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ihadzhi.eatlimination.data.Diet
import com.ihadzhi.eatlimination.data.Food
import java.util.concurrent.Executors

class FoodsIncludedViewModel(application: Application) : BaseViewModel(application) {

    private var foods: LiveData<List<Food>>? = null

    var activeDiet: LiveData<Diet>? = null
        get() {
            if (field == null) {
                field = database.dietDao().fetchActiveDiet()
            }
            return field
        }
        private set

    fun getFoods(dietId: Long): LiveData<List<Food>>? {
        if (foods == null) {
            foods = MutableLiveData()
            foods = database.foodDao().fetchByDietId(dietId)
        }
        return foods
    }

    fun removeFoodFromDiet(food: Food) {
        food.dietId = -1
        Executors.newSingleThreadScheduledExecutor().execute { database.foodDao().updateFood(food) }
    }
}