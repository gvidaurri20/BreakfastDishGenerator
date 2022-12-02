package edu.utap.breakfastdishgenerator.ui

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import edu.utap.breakfastdishgenerator.api.DishPostInfo
import edu.utap.breakfastdishgenerator.api.IngredientInfo
import edu.utap.breakfastdishgenerator.auth.FirestoreAuthLiveData
import edu.utap.breakfastdishgenerator.glide.Glide

class MainViewModel : ViewModel() {
    private var displayName = MutableLiveData("Uninitialized")
    private var email = MutableLiveData("Uninitialized")
    private var uid = MutableLiveData("Uninitialized")

    private val ingredientsList = mutableListOf<String>()
    private val ingredientsListAsIngredientInfos = mutableListOf<IngredientInfo>()

    private val dishPostInfos = MutableLiveData<List<DishPostInfo>>()
    // Maintain a separate list of all favorite dish posts
    private var favDishPostInfos = mutableListOf<DishPostInfo>()

    var whichFragmentUserIsCurrentlyViewing: Int = 0  // Values for which fragment user is currently viewing: 0 for DishesRelatedToIngredients, 1 for FavoriteDishes
    var whichIngredientFragmentUserIsCurrentlyViewing: Int = 0 // Values for which fragment user is currently viewing for ingredients to update RecyclerView: 0 for FindDishesToMake, 1 for AddIngredientFragment

    var fetchDone : MutableLiveData<Boolean> = MutableLiveData(false)

    // Database access
    private val dbHelp = ViewModelDBHelper()
    private var firebaseAuthLiveData = FirestoreAuthLiveData()


    // Methods involving the user information
    private fun userLogout() {
        displayName.postValue("No user")
        email.postValue("No email, no active user")
        uid.postValue("No uid, no active user")
    }
    fun updateUser() {
        val user = FirebaseAuth.getInstance().currentUser
        displayName.postValue(user?.displayName)
        email.postValue(user?.email)
        uid.postValue(user?.uid)
    }
    fun observeDisplayName() : LiveData<String> {
        return displayName
    }
    fun observeEmail() : LiveData<String> {
        return email
    }
    fun observeUid() : LiveData<String> {
        return uid
    }
    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        userLogout()
    }

    // Methods to get list values
    fun getDishPostInfoAt(position: Int) : DishPostInfo {
        if (whichFragmentUserIsCurrentlyViewing == 0) {
            return dishPostInfos.value!![position]
        }
        else { //if (whichFragmentUserIsCurrentlyViewing == 1) {
            return favDishPostInfos[position]
        }
    }

    // Methods to observe the lists
    fun observeDishPostInfos(): LiveData<List<DishPostInfo>> {
        return dishPostInfos
    }
    fun observeFavDishPostInfos(): LiveData<List<DishPostInfo>> {
        var liveDataFavDishPostInfo = MutableLiveData<List<DishPostInfo>>(favDishPostInfos)
        return liveDataFavDishPostInfo
    }
    fun observeIngredientsList(): LiveData<List<String>> {
        var liveDataIngredientsList = MutableLiveData<List<String>>(ingredientsList)
        return liveDataIngredientsList
    }
    fun observeIngredientsListAsIngredientInfos(): LiveData<List<IngredientInfo>> {
        var liveDataIngredientsListAsIngredientInfos = MutableLiveData<List<IngredientInfo>>(ingredientsListAsIngredientInfos)
        return liveDataIngredientsListAsIngredientInfos
    }

    // Methods to manage the added ingredients list
    fun isIngredientListEmpty(): Boolean {
        return ingredientsList.isEmpty()
    }
    fun getIngredientListCount(): Int {
        return ingredientsList.count()
    }
    fun isIngredientAlreadyAdded(ingredientName: String): Boolean {
        return ingredientsList.contains(ingredientName)
    }
    fun addIngredientToList(ingredientName: String) {
        ingredientsList.add(ingredientName)
        ingredientsListAsIngredientInfos.add(IngredientInfo(ingredientName))
    }
    fun removeIngredientFromList(ingredientName: String) {
        ingredientsList.remove(ingredientName)
        ingredientsListAsIngredientInfos.remove(IngredientInfo(ingredientName))
    }

    // Methods to manage the favorites list
    fun isFavorite(dishPostInfo: DishPostInfo): Boolean {
        return favDishPostInfos.contains(dishPostInfo)
    }
    fun addFavorite(dishPostInfo: DishPostInfo) {
        favDishPostInfos.add(dishPostInfo)
    }
    fun removeFavorite(dishPostInfo: DishPostInfo) {
        favDishPostInfos.remove(dishPostInfo)
    }

    fun fetchDishMeta() {
        dbHelp.dbFetchDishMeta(dishPostInfos)
    }

    fun glideFetch(imageName: String, imageView: ImageView) {
        // Create a storage reference from our app
        val photoStorage: StorageReference =
            FirebaseStorage.getInstance().reference.child("dishImages")
        Glide.fetch(photoStorage.child(imageName),
            imageView)
    }

    // Convenient place to put it as it is shared
    companion object {
        fun moveOnToDishPostScreen(context: Context, dishPost: DishPostInfo) {
            val dishPostIntent = Intent(context, DishPostInfoScreen::class.java)
            dishPostIntent.putExtra("TheDishPostTitle", dishPost.TitleOfDish.toString())
            dishPostIntent.putExtra("TheDishPostImageUUID", dishPost.DishImageName.toString())
            dishPostIntent.putExtra("TheDishPostNutritionInfo", dishPost.NutritionInfoOfDish.toString())
            dishPostIntent.putExtra("TheDishPostAmtOfServings", dishPost.AmountOfServingsPerDish.toString())
            dishPostIntent.putExtra("TheDishPostIngredientsNeeded", dishPost.IngredientsNeededPerDish.toString())
            dishPostIntent.putExtra("TheDishPostRecipe", dishPost.RecipeOfDish.toString())
            context.startActivity(dishPostIntent)
        }
    }
}