package edu.utap.breakfastdishgenerator.ui

import android.R
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
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

        val builder = SpannableStringBuilder()
        var str1 = SpannableString("Nutrition Info: \n")
        str1.setSpan(ForegroundColorSpan(Color.RED), 0, str1.length, 0)
        builder.append(str1)
        var str2 = SpannableString(dishPostNutritionInfo.replace("\\n", "\n"))
        str2.setSpan(ForegroundColorSpan(Color.LTGRAY), 0, str2.length, 0)
        builder.append(str2)
        binding.nutritionInfoTextview.text = builder//"Nutrition Info: \n" + dishPostNutritionInfo.replace("\\n", "\n")

        val builder2 = SpannableStringBuilder()
        str1 = SpannableString("Servings Per Dish: \n")
        str1.setSpan(ForegroundColorSpan(Color.RED), 0, str1.length, 0)
        builder2.append(str1)
        str2 = SpannableString(dishPostAmtOfServings.replace("\\n", "\n"))
        str2.setSpan(ForegroundColorSpan(Color.LTGRAY), 0, str2.length, 0)
        builder2.append(str2)
        binding.servingsTextview.text = builder2//"Servings Per Dish: \n" + dishPostAmtOfServings.replace("\\n", "\n")

        val builder3 = SpannableStringBuilder()
        str1 = SpannableString("Ingredients Needed: \n")
        str1.setSpan(ForegroundColorSpan(Color.RED), 0, str1.length, 0)
        builder3.append(str1)
        str2 = SpannableString(dishPostIngredientsNeeded.replace("\\n", "\n"))
        str2.setSpan(ForegroundColorSpan(Color.LTGRAY), 0, str2.length, 0)
        builder3.append(str2)
        binding.ingredientsNeededTextview.text = builder3//"Ingredients Needed: \n" + dishPostIngredientsNeeded.replace("\\n", "\n")

        val builder4 = SpannableStringBuilder()
        str1 = SpannableString("General Recipe: \n")
        str1.setSpan(ForegroundColorSpan(Color.RED), 0, str1.length, 0)
        builder4.append(str1)
        str2 = SpannableString(dishPostRecipe.replace("\\n", "\n"))
        str2.setSpan(ForegroundColorSpan(Color.LTGRAY), 0, str2.length, 0)
        builder4.append(str2)
        binding.genericRecipeTextview.text = builder4//"General Recipe: \n" + dishPostRecipe.replace("\\n", "\n")
    }

}