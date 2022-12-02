package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.api.IngredientInfo
import edu.utap.breakfastdishgenerator.databinding.FindDishesToMakeBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FindDishesToMakeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FindDishesToMakeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: IngredientRowAdapter

    companion object {
        fun newInstance(): FindDishesToMakeFragment {
            return FindDishesToMakeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FindDishesToMakeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = activity as MainActivity?
        val actionBarBinding = main?.actionBarBinding
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Find Dishes to Make"

        if(viewModel.isIngredientListEmpty()) {
            binding.ingredientsCurrentlyAddedTv.text = "No Ingredients Have Been Added Yet"
        }
        else {
            println("count of list: " + viewModel.getIngredientListCount())
            binding.ingredientsCurrentlyAddedTv.text = "(If There Are A Lot, You May Need to Scroll to View All of Them)"
        }

        // XXX Write me
        // Setting itemAnimator = null on your recycler view might get rid of an annoying
        // flicker
        binding.recyclerView.itemAnimator = null
        adapter = IngredientRowAdapter(viewModel, binding.recyclerView.context)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context)
        binding.recyclerView.adapter = adapter

        viewModel.observeIngredientsListAsIngredientInfos().observe(viewLifecycleOwner) {
            println("viewModel.observeIngredientsListAsIngredientInfos().value: " + viewModel.observeIngredientsListAsIngredientInfos().value)
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        binding.swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.fetchDone.observe(viewLifecycleOwner) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        binding.addIngredientButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AddIngredientsByFoodGroupFragment.newInstance(),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }

        binding.findDishesButton.setOnClickListener {
            if(viewModel.isIngredientListEmpty()) {
                Toast.makeText(this.context, "No Ingredients Have Been Added", Toast.LENGTH_LONG).show()
            }
            else {
                println("Ingredients we will search for: " + viewModel.observeIngredientsList().value)
                viewModel.fetchDishMeta()
                println("got here1: " + viewModel.observeDishPostInfos().value)

                parentFragmentManager.commit {
                    replace(
                        R.id.main_frame, DishesRelatedToIngredientsFragment.newInstance(),
                        MainActivity.mainFragTag
                    )
                    // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    addToBackStack(null)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}