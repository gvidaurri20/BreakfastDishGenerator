package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.databinding.AddIngredientsByFoodGroupBinding

class AddIngredientsByFoodGroupFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: AddIngredientsByFoodGroupBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): AddIngredientsByFoodGroupFragment {
            return AddIngredientsByFoodGroupFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddIngredientsByFoodGroupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = activity as MainActivity?
        val actionBarBinding = main?.actionBarBinding
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Add Ingredient By Food Group"

        binding.fruitsButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Fruits"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
        binding.vegetablesButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Vegetables"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
        binding.grainsButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Grains"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
        binding.proteinsButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Proteins"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
        binding.dairyButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Dairy"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
        binding.sweetsButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Sweets"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
        binding.othersButton.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Others"),
                    MainActivity.mainFragTag
                )
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}