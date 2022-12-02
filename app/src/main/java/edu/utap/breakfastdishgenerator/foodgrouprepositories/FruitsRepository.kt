package edu.utap.breakfastdishgenerator

import edu.utap.breakfastdishgenerator.api.IngredientInfo

class FruitsRepository {
    private var fruitResources = hashMapOf(
        "Apples" to
                IngredientInfo("Apples"),
        "Bananas" to
                IngredientInfo("Bananas"),
        "Berries" to
                IngredientInfo("Berries"),
        "Blackberries" to
                IngredientInfo("Blackberries"),
        "Blueberries" to
                IngredientInfo("Blueberries"),
        "Cantaloupes" to
                IngredientInfo("Cantaloupes"),
        "Cherries" to
                IngredientInfo("Cherries"),
        "Coconuts" to
                IngredientInfo("Coconuts"),
        "Dates" to
                IngredientInfo("Dates"),
        "Grapes" to
                IngredientInfo("Grapes"),
        "Kiwis" to
                IngredientInfo("Kiwis"),
        "Lemons" to
                IngredientInfo("Lemons"),
        "Mangoes" to
                IngredientInfo("Mangoes"),
        "Olives" to
                IngredientInfo("Olives"),
        "Oranges" to
                IngredientInfo("Oranges"),
        "Peaches" to
                IngredientInfo("Peaches"),
        "Pineapples" to
                IngredientInfo("Pineapples"),
        "Pomegranates" to
                IngredientInfo("Pomegranates"),
        "Pumpkins" to
                IngredientInfo("Pumpkins"),
        "Strawberries" to
                IngredientInfo("Strawberries"),
        "Raspberries" to
                IngredientInfo("Raspberries"),
    )
    fun fetchData(): HashMap<String, IngredientInfo> {
        return fruitResources
    }
}