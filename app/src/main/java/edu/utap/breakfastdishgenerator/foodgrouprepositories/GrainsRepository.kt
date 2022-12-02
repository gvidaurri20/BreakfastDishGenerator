package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class GrainsRepository {
    private var grainsResources = hashMapOf(
        "Bread" to
                IngredientInfo("Bread"),
        "Brown Rice" to
                IngredientInfo("Brown Rice"),
        "Oatmeal" to
                IngredientInfo("Oatmeal"),
        "Popcorn" to
                IngredientInfo("Popcorn"),
        "Pretzels" to
                IngredientInfo("Pretzels"),
        "White Rice" to
                IngredientInfo("White Rice")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return grainsResources
    }
}