package edu.utap.breakfastdishgenerator.api

data class DishPostInfo (
    /*//@SerializedName("title_of_dish")
    var nameOfDish: String,
    //@SerializedName("thumbnail_of_dish")
    var nutritionInfo: String,
   // @SerializedName("imageurl_of_dish")
    var amtOfServings: String,
    var ingredientsNeeded: String,
    //@SerializedName("generic_recipe_of_dish")
    var genericRecipeOfDish: String,*/

    var AmountOfServingsPerDish: String = "",
    var DishImageName: String = "",
    var DishNumber: String = "",
    var IngredientsNeededPerDish: String = "",
    var NutritionInfoOfDish: String = "",
    var RecipeOfDish: String = "",
    var SearchTags: String = "",
    var TitleOfDish: String = ""
) {
    // NB: This changes the behavior of lists of DishPosts.  I want posts fetched
    // at two different times to compare as equal.  By default, they will be different
    // objects with different hash codes.
    override fun equals(other: Any?) : Boolean =
        if (other is DishPostInfo) {
            TitleOfDish == other.TitleOfDish
        } else {
            false
        }
}
