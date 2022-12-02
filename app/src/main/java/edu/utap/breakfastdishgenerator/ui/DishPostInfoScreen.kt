package edu.utap.breakfastdishgenerator.ui

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.utap.breakfastdishgenerator.databinding.ActivityDishInfoBinding


class DishPostInfoScreen : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding : ActivityDishInfoBinding

    // This function executes when the user presses the actual Back button from the Android device
    override fun onBackPressed() {
        finish()
    }

    // This function executes when the user clicks on the Back arrow button on the action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the Intent that called for this Activity to open
        val activityThatCalled = intent
        // Get the data that was sent
        val callingBundle = activityThatCalled.extras

        var dishPostTitle = callingBundle?.getString("TheDishPostTitle").toString()
        var dishPostImage = callingBundle?.getString("TheDishPostImageUUID").toString()
        var dishPostNutritionInfo = callingBundle?.getString("TheDishPostNutritionInfo").toString()
        var dishPostAmtOfServings = callingBundle?.getString("TheDishPostAmtOfServings").toString()
        var dishPostIngredientsNeeded = callingBundle?.getString("TheDishPostIngredientsNeeded").toString()
        var dishPostRecipe = callingBundle?.getString("TheDishPostRecipe").toString()

        // Set the layout for the layout we created
        binding = ActivityDishInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleOfDishTextview.text = dishPostTitle.replace("\\n", "\n")
        viewModel.glideFetch(dishPostImage, binding.imageOfDish)
        binding.nutritionInfoTextview.text = "Nutrition Info: \n" + dishPostNutritionInfo.replace("\\n", "\n")
        binding.servingsTextview.text = "Servings Per Dish: \n" + dishPostAmtOfServings.replace("\\n", "\n")
        binding.ingredientsNeededTextview.text = "Ingredients Needed: \n" + dishPostIngredientsNeeded.replace("\\n", "\n")
        binding.genericRecipeTextview.text = "General Recipe: \n" + dishPostRecipe.replace("\\n", "\n")
    }

}