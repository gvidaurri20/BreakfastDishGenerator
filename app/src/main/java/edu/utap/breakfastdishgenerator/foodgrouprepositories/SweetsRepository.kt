package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class SweetsRepository {
    private var sweetsResources = hashMapOf(
        "Chocolate" to
                IngredientInfo("Chocolate"),
        "Honey" to
                IngredientInfo("Honey"),
        "Sugar" to
                IngredientInfo("Sugar"),
        "Syrup" to
                IngredientInfo("Syrup")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return sweetsResources
    }
}