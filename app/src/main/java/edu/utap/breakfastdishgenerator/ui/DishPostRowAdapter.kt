package edu.utap.breakfastdishgenerator.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.api.DishPostInfo
import edu.utap.breakfastdishgenerator.databinding.RowDishpostBinding


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

    private fun getPos(holder: ViewHolder) : Int {
        val pos = holder.adapterPosition
        if(pos == RecyclerView.NO_POSITION) {
            return holder.adapterPosition
        }
        return pos
    }

    // ViewHolder pattern minimizes calls to findViewById
    inner class ViewHolder(val rowDishPostBinding: RowDishpostBinding)
        : RecyclerView.ViewHolder(rowDishPostBinding.root) {
            init {
                rowDishPostBinding.titleOfDish.setOnClickListener {
                    MainViewModel.Companion.moveOnToDishPostScreen(it.context, viewModel.getDishPostInfoAt(getPos(this)))
                }
                rowDishPostBinding.imageOfDish.setOnClickListener {
                    MainViewModel.Companion.moveOnToDishPostScreen(it.context, viewModel.getDishPostInfoAt(getPos(this)))
                }
                rowDishPostBinding.rowFav.setOnClickListener {
                    val position = getPos(this)
                    // Toggle Favorite
                    val local = viewModel.getDishPostInfoAt(position)
                    local.let {
                        if(viewModel.whichDishesFragmentUserIsCurrentlyViewing == 0) {
                            if (viewModel.isFavorite(it)) {
                                viewModel.removeFavorite(it)
                            } else {
                                viewModel.addFavorite(it)
                            }
                            notifyItemChanged(position)
                        }
                        else if(viewModel.whichDishesFragmentUserIsCurrentlyViewing == 1) {
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
        if (viewModel.whichDishesFragmentUserIsCurrentlyViewing == 1 && (position >= viewModel.getFavoriteCountSize())) { // Resolves a bug where a position gets passed in that is beyond the size of the favorites list
            Log.d(javaClass.simpleName, "Argument for position passed to onBindViewHolder() was outside max index of favorites list. \nMax Index of Favorites List: "
                    + viewModel.getFavoriteCountSize() + "\nPosition Argument Passed In: " + position)
            return
        }
        if (viewModel.whichDishesFragmentUserIsCurrentlyViewing == 0 && (position >= viewModel.getDishPostInfosCountSize())) { // Resolves a bug where a position gets passed in that is beyond the size of the dish post lists
            Log.d(javaClass.simpleName, "Argument for position passed to onBindViewHolder() was outside max index of dish posts list. \nMax Index of Dish Post List: "
                    + viewModel.getDishPostInfosCountSize () + "\nPosition Argument Passed In: " + position)
            return
        }

        val dishPostInfo = viewModel.getDishPostInfoAt(position)
        val binding = holder.rowDishPostBinding
        binding.titleOfDish.text = dishPostInfo.TitleOfDish
        viewModel.glideFetch(viewModel.getDishPostInfoAt(position).DishImageName, binding.imageOfDish)
        if (viewModel.isFavorite(dishPostInfo)) {
            binding.rowFav.setImageResource(R.drawable.ic_favorite_blue_24dp)
        } else {
            binding.rowFav.setImageResource(R.drawable.ic_favorite_border_blue_24dp)
        }
    }
}
