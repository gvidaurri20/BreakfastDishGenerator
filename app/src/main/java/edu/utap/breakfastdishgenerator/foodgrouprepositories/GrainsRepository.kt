package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class GrainsRepository {
    private var grainsResources = hashMapOf(
        "Bread" to
                IngredientInfo("Bread"),
        "Cereal" to
                IngredientInfo("Cereal"),
        "Quinoa" to
                IngredientInfo("Quinoa"),
        "Muffins" to
                IngredientInfo("Muffins"),
        "Oats" to
                IngredientInfo("Oats"),
        "Tortillas" to
                IngredientInfo("Tortillas")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return grainsResources
    }
}