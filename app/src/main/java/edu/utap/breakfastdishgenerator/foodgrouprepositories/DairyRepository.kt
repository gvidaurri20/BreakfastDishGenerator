package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class DairyRepository {
    private var dairyResources = hashMapOf(
        "Butter" to
                IngredientInfo("Butter"),
        "Cheese" to
                IngredientInfo("Cheese"),
        "Cream" to
                IngredientInfo("Cream"),
        "Milk" to
                IngredientInfo("Milk"),
        "Yogurt" to
                IngredientInfo("Yogurt")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return dairyResources
    }
}