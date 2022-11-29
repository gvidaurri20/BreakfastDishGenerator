package edu.utap.breakfastdishgenerator.api

import com.google.gson.annotations.SerializedName

data class DishPostInfo (
    @SerializedName("name_of_dish")
    val nameOfDish: String,
    @SerializedName("thumbnail_of_dish")
    val thumbnailURL: String,
    @SerializedName("imageurl_of_dish")
    val imageURL: String,
    @SerializedName("generic_recipe_of_dish")
    val genericRecipeOfDish: String,
) {
    // NB: This changes the behavior of lists of RedditPosts.  I want posts fetched
    // at two different times to compare as equal.  By default, they will be different
    // objects with different hash codes.
    override fun equals(other: Any?) : Boolean =
        if (other is DishPostInfo) {
            nameOfDish == other.nameOfDish
        } else {
            false
        }
}
