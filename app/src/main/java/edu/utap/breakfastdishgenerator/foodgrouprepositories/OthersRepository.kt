package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class OthersRepository {
    private var othersResources = hashMapOf(
        "Canola Oil" to
                IngredientInfo("Canola Oil"),
        "Cashews" to
                IngredientInfo("Cashews"),
        "Cinnamon" to
                IngredientInfo("Cinnamon"),
        "Ice" to
                IngredientInfo("Ice"),
        "Salt" to
                IngredientInfo("Salt"),
        "Vanilla" to
                IngredientInfo("Vanilla"),
        "Vinegar" to
                IngredientInfo("Vinegar"),
        "Water" to
                IngredientInfo("Water"),
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return othersResources
    }
}