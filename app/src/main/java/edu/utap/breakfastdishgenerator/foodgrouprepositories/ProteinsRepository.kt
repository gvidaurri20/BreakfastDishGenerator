package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class ProteinsRepository {
    private var proteinResources = hashMapOf(
        "Beef" to
                IngredientInfo("Beef"),
        "Broccoli" to
                IngredientInfo("Broccoli"),
        "Chicken" to
                IngredientInfo("Chicken"),
        "Eggs" to
                IngredientInfo("Eggs"),
        "Mackerel" to
                IngredientInfo("Mackerel"),
        "Nuts" to
                IngredientInfo("Nuts"),
        "Pork" to
                IngredientInfo("Pork"),
        "Salmon" to
                IngredientInfo("Salmon"),
        "Sardines" to
                IngredientInfo("Sardines"),
        "Seeds" to
                IngredientInfo("Seeds"),
        "Shrimp" to
                IngredientInfo("Shrimp"),
        "Soy Products" to
                IngredientInfo("Soy Products"),
        "Tilapia" to
                IngredientInfo("Tilapia"),
        "Trout" to
                IngredientInfo("Trout"),
        "Tuna" to
                IngredientInfo("Tuna"),
        "Turkey" to
                IngredientInfo("Turkey"),
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return proteinResources
    }
}