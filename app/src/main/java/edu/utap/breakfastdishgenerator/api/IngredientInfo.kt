package edu.utap.breakfastdishgenerator.api

data class IngredientInfo (
    val nameOfIngredient: String
) {
    // NB: This changes the behavior of lists of RedditPosts.  I want posts fetched
    // at two different times to compare as equal.  By default, they will be different
    // objects with different hash codes.
    override fun equals(other: Any?) : Boolean =
        if (other is IngredientInfo) {
            nameOfIngredient == other.nameOfIngredient
        } else {
            false
        }
}
