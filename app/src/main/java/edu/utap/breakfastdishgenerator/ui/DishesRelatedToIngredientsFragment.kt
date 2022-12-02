package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.databinding.FragmentRvBinding


// XXX Write most of this file
class DishesRelatedToIngredientsFragment: Fragment() {
    // XXX initialize viewModel
    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentRvBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: DishPostRowAdapter

    companion object {
        fun newInstance(): DishesRelatedToIngredientsFragment {
            return DishesRelatedToIngredientsFragment()
        }
    }

    // Set up the adapter
    private fun initAdapter(binding: FragmentRvBinding) : DishPostRowAdapter {
        adapter = DishPostRowAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context)
        binding.recyclerView.adapter = adapter

        return adapter
    }

    private fun notifyWhenFragmentForegrounded(dishPostRowAdapter: DishPostRowAdapter) {
        // When we return to our fragment, notifyDataSetChanged
        // to pick up modifications to the favorites list.  Maybe do more.
        viewModel.observeDishPostInfos().observe(viewLifecycleOwner) {
            dishPostRowAdapter.submitList(it)
            dishPostRowAdapter.notifyDataSetChanged()
        }
    }

    private fun initSwipeLayout(swipe : SwipeRefreshLayout) {
        swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            //viewModel.netPosts()

            viewModel.fetchDone.observe(viewLifecycleOwner) {
                swipe.isRefreshing = false
            }

            viewModel.observeDishPostInfos().observe(viewLifecycleOwner) {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.simpleName, "onViewCreated")
        // XXX Write me
        adapter = initAdapter(binding)

        val main = activity as MainActivity?
        val actionBarBinding = main?.actionBarBinding
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Dishes Related to Ingredients"

        //viewModel.netPosts()

        binding.recyclerView.itemAnimator = null

        viewModel.fetchDishMeta()
        println("got here2: " + viewModel.observeDishPostInfos().value)

        viewModel.observeDishPostInfos().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        initSwipeLayout(binding.swipeRefreshLayout)
    }
}