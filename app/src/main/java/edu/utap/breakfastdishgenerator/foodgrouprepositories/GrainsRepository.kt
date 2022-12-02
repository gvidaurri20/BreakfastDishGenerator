package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class GrainsRepository {
    private var grainsResources = hashMapOf(
        "Bread" to
                IngredientInfo("Bread"),
        "Cereal" to
                IngredientInfo("Cereal"),
        "Muffins" to
                IngredientInfo("Muffins"),
        "Oats" to
                IngredientInfo("Oats"),
        "Pancakes" to
                IngredientInfo("Pancakes"),
        "Quinoa" to
                IngredientInfo("Quinoa"),
        "Tortillas" to
                IngredientInfo("Tortillas")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return grainsResources
    }
}