package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

// This is the model in MVVM
//data class VegetableInfo(val name: String)
class VegetablesRepository {
    private var vegetableResources = hashMapOf(
        "Avocado" to
                IngredientInfo("Avocado"),
        "Black Beans" to
                IngredientInfo("Black Beans"),
        "Broccoli" to
                IngredientInfo("Broccoli"),
        "Carrots" to
                IngredientInfo("Carrots"),
        "Corn" to
                IngredientInfo("Corn"),
        "Lettuce" to
                IngredientInfo("Lettuce"),
        "Mushrooms" to
                IngredientInfo("Mushrooms"),
        "Peas" to
                IngredientInfo("Peas"),
        "Red Peppers" to
                IngredientInfo("Red Peppers"),
        "Spinach" to
                IngredientInfo("Spinach"),
        "Squash" to
                IngredientInfo("Squash"),
        "Sweet Potatoes" to
                IngredientInfo("Sweet Potatoes"),
        "Tomatoes" to
                IngredientInfo("Tomatoes")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return vegetableResources
    }
}