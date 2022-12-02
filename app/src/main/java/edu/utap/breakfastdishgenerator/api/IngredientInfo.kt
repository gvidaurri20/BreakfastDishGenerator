package edu.utap.breakfastdishgenerator.api

data class IngredientInfo (
    val nameOfIngredient: String
) {
    override fun equals(other: Any?) : Boolean =
        if (other is IngredientInfo) {
            nameOfIngredient == other.nameOfIngredient
        } else {
            false
        }
}
