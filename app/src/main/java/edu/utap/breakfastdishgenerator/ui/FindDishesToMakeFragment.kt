package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.databinding.FindDishesToMakeBinding


class FindDishesToMakeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FindDishesToMakeBinding? = null
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
            binding.ingredientsCurrentlyAddedTv.text = "(If There Are A Lot, You May Need to Scroll to View All of Them)"
        }

        binding.recyclerView.itemAnimator = null
        adapter = IngredientRowAdapter(viewModel, binding.recyclerView.context)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context)
        binding.recyclerView.adapter = adapter

        viewModel.observeIngredientsListAsIngredientInfos().observe(viewLifecycleOwner) {
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
                addToBackStack(null)
            }
        }

        binding.findDishesButton.setOnClickListener {
            if(viewModel.isIngredientListEmpty()) {
                Toast.makeText(this.context, "No Ingredients Have Been Added", Toast.LENGTH_LONG).show()
            }
            else {
                viewModel.fetchDishMeta()

                viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
                parentFragmentManager.commit {
                    replace(
                        R.id.main_frame, DishesRelatedToIngredientsFragment.newInstance(),
                        MainActivity.mainFragTag
                    )
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