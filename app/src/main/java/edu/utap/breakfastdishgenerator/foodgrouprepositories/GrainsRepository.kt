package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

// This is the model in MVVM
//data class GrainInfo(val name: String)
class GrainsRepository {
    private var grainsResources = hashMapOf(
        "Brown Rice" to
                IngredientInfo("Brown Rice"),
        "Oatmeal" to
                IngredientInfo("Oatmeal"),
        "Popcorn" to
                IngredientInfo("Popcorn"),
        "Pretzels" to
                IngredientInfo("Pretzels"),
        "White Rice" to
                IngredientInfo("White Rice"),
        "Whole Wheat Bread" to
                IngredientInfo("Whole Wheat Bread")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return grainsResources
    }
}