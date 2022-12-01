package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.databinding.AddIngredientsByFoodGroupBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddIngredientsByFoodGroupFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: AddIngredientsByFoodGroupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Fruits"),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
        binding.vegetablesButton.setOnClickListener {
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Vegetables"),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
        binding.grainsButton.setOnClickListener {
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Grains"),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
        binding.proteinsButton.setOnClickListener {
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Proteins"),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
        binding.dairyButton.setOnClickListener {
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 1
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientFragment.newInstance("Dairy"),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}