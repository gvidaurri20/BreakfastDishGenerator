package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class VegetablesRepository {
    private var vegetableResources = hashMapOf(
        "Asparagus" to
                IngredientInfo("Asparagus"),
        "Avocados" to
                IngredientInfo("Avocados"),
        "Beans" to
                IngredientInfo("Beans"),
        "Broccoli" to
                IngredientInfo("Broccoli"),
        "Carrots" to
                IngredientInfo("Carrots"),
        "Cilantro" to
                IngredientInfo("Carrots"),
        "Garlic" to
                IngredientInfo("Garlic"),
        "Ginger" to
                IngredientInfo("Ginger"),
        "Jalapenos" to
                IngredientInfo("Jalapenos"),
        "Kale" to
                IngredientInfo("Kale"),
        "Lettuce" to
                IngredientInfo("Lettuce"),
        "Mushrooms" to
                IngredientInfo("Mushrooms"),
        "Onions" to
                IngredientInfo("Onions"),
        "Peas" to
                IngredientInfo("Peas"),
        "Peppers" to
                IngredientInfo("Peppers"),
        "Red Peppers" to
                IngredientInfo("Red Peppers"),
        "Spinach" to
                IngredientInfo("Spinach"),
        "Tomatoes" to
                IngredientInfo("Tomatoes")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return vegetableResources
    }
}