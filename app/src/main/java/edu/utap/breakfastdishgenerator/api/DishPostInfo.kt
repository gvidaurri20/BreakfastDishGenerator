package edu.utap.breakfastdishgenerator.api

data class DishPostInfo (
    var AmountOfServingsPerDish: String = "",
    var DishImageName: String = "",
    var DishNumber: String = "",
    var IngredientsNeededPerDish: String = "",
    var NutritionInfoOfDish: String = "",
    var RecipeOfDish: String = "",
    var SearchTags: String = "",
    var TitleOfDish: String = "",
) {
    override fun equals(other: Any?) : Boolean =
        if (other is DishPostInfo) {
            TitleOfDish == other.TitleOfDish
        } else {
            false
        }
}
