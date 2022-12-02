package edu.utap.breakfastdishgenerator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.api.DishPostInfo
//import edu.utap.breakfastdishgenerator.glide.Glide
import edu.utap.breakfastdishgenerator.databinding.RowDishpostBinding
import edu.utap.breakfastdishgenerator.glide.Glide

// https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
// Slick adapter that provides submitList, so you don't worry about how to update
// the list, you just submit a new one when you want to change the list and the
// Diff class computes the smallest set of changes that need to happen.
// NB: Both the old and new lists must both be in memory at the same time.
// So you can copy the old list, change it into a new list, then submit the new list.
//
// You can call adapterPosition to get the index of the selected item

class DishPostRowAdapter(private val viewModel: MainViewModel)
    : ListAdapter<DishPostInfo, DishPostRowAdapter.ViewHolder>(DishDiff()) {
    class DishDiff : DiffUtil.ItemCallback<DishPostInfo>() {
        // Item identity
        override fun areItemsTheSame(oldItem: DishPostInfo, newItem: DishPostInfo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
        // Item contents are the same, but the object might have changed
        override fun areContentsTheSame(oldItem: DishPostInfo, newItem: DishPostInfo): Boolean {
            return oldItem.TitleOfDish == newItem.TitleOfDish
                    && oldItem.NutritionInfoOfDish == newItem.NutritionInfoOfDish
                    && oldItem.AmountOfServingsPerDish == newItem.AmountOfServingsPerDish
                    && oldItem.IngredientsNeededPerDish == newItem.IngredientsNeededPerDish
                    && oldItem.RecipeOfDish == newItem.RecipeOfDish
        }
    }

    // https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder#getBindingAdapterPosition()
    // Getting the position of the selected item is unfortunately complicated
    // This always returns a valid index.
    private fun getPos(holder: ViewHolder) : Int {
        val pos = holder.adapterPosition
        // notifyDataSetChanged was called, so position is not known
        if( pos == RecyclerView.NO_POSITION) {
            return holder.adapterPosition
        }
        return pos
    }

    // ViewHolder pattern minimizes calls to findViewById
    inner class ViewHolder(val rowDishPostBinding: RowDishpostBinding)
        : RecyclerView.ViewHolder(rowDishPostBinding.root) {
            init {
                /*if(viewModel.firstTimeHomeFragmentGetsCalled  == true) { // We only want to initialize the reddit posts and favorites search list once
                    viewModel.initializeRedditPostAndFavoritesSearchLists()
                    viewModel.firstTimeHomeFragmentGetsCalled = false
                }*/
                rowDishPostBinding.titleOfDish.setOnClickListener {
                    println("clicked the title of the dish!")
                    MainViewModel.Companion.moveOnToDishPostScreen(it.context, viewModel.getDishPostInfoAt(getPos(this)))
                }
                rowDishPostBinding.rowFav.setOnClickListener {
                    val position = getPos(this)
                    // Toggle Favorite
                    val local = viewModel.getDishPostInfoAt(position)
                    local.let {
                        if(viewModel.whichFragmentUserIsCurrentlyViewing == 0) {
                            if (viewModel.isFavorite(it)) {
                                viewModel.removeFavorite(it)
                            } else {
                                viewModel.addFavorite(it)
                            }
                            notifyItemChanged(position)
                        }
                        else if(viewModel.whichFragmentUserIsCurrentlyViewing == 1) {
                            if (viewModel.isFavorite(it)) {
                                viewModel.removeFavorite(it)
                            }
                            notifyDataSetChanged()
                        }

                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowDishpostBinding = RowDishpostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(rowDishpostBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*if (viewModel.whichFragmentUserIsCurrentlyViewing == 1 && (position >= viewModel.getFavoriteCountSize())) { // Resolves a bug where a position gets passed in that is beyond the size of the favorites list
            Log.d(javaClass.simpleName, "Argument for position passed to onBindViewHolder() was outside max index of favorites list. \nMax Index of Favorites List: "
                    + viewModel.getFavoriteCountSize() + "\nPosition Argument Passed In: " + position)
            return
        }
        if (viewModel.whichFragmentUserIsCurrentlyViewing == 1 && (position >= viewModel.getSearchedFavoriteCountSize())) { // Resolves a bug where a position gets passed in that is beyond the size of the searched favorites list
            Log.d(javaClass.simpleName, "Argument for position passed to onBindViewHolder() was outside max index of searched favorites list. \nMax Index of Searched Favorites List: "
                    + viewModel.getSearchedFavoriteCountSize() + "\nPosition Argument Passed In: " + position)
            return
        }
        if (viewModel.whichFragmentUserIsCurrentlyViewing == 0 && (position >= viewModel.getRedditPostsCountSize())) { // Resolves a bug where a position gets passed in that is beyond the size of the reddit post lists
            Log.d(javaClass.simpleName, "Argument for position passed to onBindViewHolder() was outside max index of searched reddit posts list. \nMax Index of Searched Reddit Post List: "
                    + viewModel.getRedditPostsCountSize() + "\nPosition Argument Passed In: " + position)
            return
        }
        if (viewModel.whichFragmentUserIsCurrentlyViewing == 2 && (position >= viewModel.getRedditPostsCountSize())) { // Resolves a bug where a position gets passed in that is beyond the size of the reddit post lists in subreddits
            Log.d(javaClass.simpleName, "Argument for position passed to onBindViewHolder() was outside max index of searched reddit posts list. \nMax Index of Searched Reddit Post List: "
                    + viewModel.getRedditPostsCountSize() + "\nPosition Argument Passed In: " + position)
            return
        }*/
        //viewModel.initializeSearchLists()
        val dishPostInfo = viewModel.getDishPostInfoAt(position)
        val binding = holder.rowDishPostBinding
        binding.titleOfDish.text = dishPostInfo.TitleOfDish
        viewModel.glideFetch("dish1.jpg", binding.imageOfDish)
        //Glide.glideFetch(dishPostInfo.imageURL, dishPostInfo.thumbnailURL, binding.imageOfDish)
        if (viewModel.isFavorite(dishPostInfo)) {
            binding.rowFav.setImageResource(R.drawable.ic_favorite_black_24dp)
        } else {
            binding.rowFav.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        }
    }
}
