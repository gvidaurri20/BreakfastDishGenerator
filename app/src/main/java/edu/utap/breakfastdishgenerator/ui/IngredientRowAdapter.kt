package edu.utap.breakfastdishgenerator.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.api.IngredientInfo
import edu.utap.breakfastdishgenerator.databinding.RowIngredientBinding


class IngredientRowAdapter(private val viewModel: MainViewModel, val context: Context?)
    : ListAdapter<IngredientInfo, IngredientRowAdapter.ViewHolder>(IngredientDiff()) {
    class IngredientDiff : DiffUtil.ItemCallback<IngredientInfo>() {
        // Item identity
        override fun areItemsTheSame(oldItem: IngredientInfo, newItem: IngredientInfo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
        // Item contents are the same, but the object might have changed
        override fun areContentsTheSame(oldItem: IngredientInfo, newItem: IngredientInfo): Boolean {
            return oldItem.nameOfIngredient == newItem.nameOfIngredient
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
    inner class ViewHolder(val rowIngredientBinding: RowIngredientBinding)
        : RecyclerView.ViewHolder(rowIngredientBinding.root) {
            init {
                if(viewModel.whichIngredientFragmentUserIsCurrentlyViewing == 0) {
                    rowIngredientBinding.addDeleteButton.text = "Delete"
                    rowIngredientBinding.addDeleteButton.setOnClickListener {
                        viewModel.removeIngredientFromList(rowIngredientBinding.nameOfIngredient.text.toString())
                        notifyDataSetChanged()
                    }
                }
                else if(viewModel.whichIngredientFragmentUserIsCurrentlyViewing == 1) {
                    rowIngredientBinding.addDeleteButton.text = "Add"
                    rowIngredientBinding.addDeleteButton.setOnClickListener {
                        if(viewModel.isIngredientAlreadyAdded(rowIngredientBinding.nameOfIngredient.text.toString())) {
                            Toast.makeText(context, "Ingredient Has Already Been Added", Toast.LENGTH_LONG).show()
                        }
                        else {
                            viewModel.addIngredientToList(rowIngredientBinding.nameOfIngredient.text.toString())
                            val activity = it.context as AppCompatActivity
                            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 0
                            viewModel.userChoosingToViewAllDishes == false
                            activity.supportFragmentManager.commit {
                                replace(
                                    R.id.main_frame, FindDishesToMakeFragment.newInstance(),
                                    MainActivity.mainFragTag
                                )
                                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                addToBackStack(null)
                            }
                        }
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowIngredientBinding = RowIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(rowIngredientBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredientInfo = getItem(position)
        val binding = holder.rowIngredientBinding
        binding.nameOfIngredient.text = ingredientInfo.nameOfIngredient }
}
