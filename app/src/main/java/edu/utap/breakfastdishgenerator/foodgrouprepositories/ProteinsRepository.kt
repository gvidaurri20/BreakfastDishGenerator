package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class ProteinsRepository {
    private var proteinResources = hashMapOf(
        "Almonds" to
                IngredientInfo("Almonds"),
        "Bacon" to
                IngredientInfo("Bacon"),
        "Beef" to
                IngredientInfo("Beef"),
        "Broccoli" to
                IngredientInfo("Broccoli"),
        "Chicken" to
                IngredientInfo("Chicken"),
        "Eggs" to
                IngredientInfo("Eggs"),
        "Ham" to
                IngredientInfo("Ham"),
        "Nuts" to
                IngredientInfo("Nuts"),
        "Peanut Butter" to
                IngredientInfo("Peanut Butter"),
        "Pecans" to
                IngredientInfo("Pecans"),
        "Salmon" to
                IngredientInfo("Salmon"),
        "Seeds" to
                IngredientInfo("Seeds"),
        "Turkey" to
                IngredientInfo("Turkey"),
        "Walnuts" to
                IngredientInfo("Walnuts"),
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return proteinResources
    }
}