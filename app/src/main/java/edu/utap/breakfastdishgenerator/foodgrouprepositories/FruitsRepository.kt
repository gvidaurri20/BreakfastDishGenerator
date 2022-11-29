package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

// This is the model in MVVM
//data class FruitInfo(val name: String)
class FruitsRepository {
    private var fruitResources = hashMapOf(
        "Apples" to
                IngredientInfo("Apples"),
        "Bananas" to
                IngredientInfo("Bananas"),
        "Blueberries" to
                IngredientInfo("Blueberries"),
        "Cantaloupes" to
                IngredientInfo("Cantaloupes"),
        "Cherries" to
                IngredientInfo("Cherries"),
        "Grapefruits" to
                IngredientInfo("Grapefruits"),
        "Grapes" to
                IngredientInfo("Grapes"),
        "Mangos" to
                IngredientInfo("Mangos"),
        "Oranges" to
                IngredientInfo("Oranges"),
        "Peaches" to
                IngredientInfo("Peaches"),
        "Pears" to
                IngredientInfo("Pears"),
        "Pineapples" to
                IngredientInfo("Pineapples"),
        "Pomegranates" to
                IngredientInfo("Pomegranates"),
        "Raisins" to
                IngredientInfo("Raisins"),
        "Strawberries" to
                IngredientInfo("Strawberries"),
        "Watermelons" to
                IngredientInfo("Watermelons")
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return fruitResources
    }
}