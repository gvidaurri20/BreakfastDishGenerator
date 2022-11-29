package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

// This is the model in MVVM
//data class DairyInfo(val name: String)
class DairyRepository {
    private var dairyResources = hashMapOf(
        "Cheese" to
                IngredientInfo("Cheese"),
        "Cottage Cheese" to
                IngredientInfo("Cottage Cheese"),
        "Milk" to
                IngredientInfo("Milk"),
        "Soy Products" to
                IngredientInfo("Soy Products"),
        "Yogurt" to
                IngredientInfo("Yogurt"),
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return dairyResources
    }
}