package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.breakfastdishgenerator.*
import edu.utap.breakfastdishgenerator.databinding.FragmentRvBinding

class AddIngredientFragment(foodGroup: String): Fragment() {
    private val fruitsRepository = FruitsRepository()
    private var fruitResources = fruitsRepository.fetchData()
    private var fruitList = fruitResources.keys.toMutableList()

    private val vegetableRepository = VegetablesRepository()
    private var vegetableResources = vegetableRepository.fetchData()
    private var vegetableList = vegetableResources.keys.toMutableList()

    private val grainsRepository = GrainsRepository()
    private var grainsResources = grainsRepository.fetchData()
    private var grainsList = grainsResources.keys.toMutableList()

    private val proteinRepository = ProteinsRepository()
    private var proteinResources = proteinRepository.fetchData()
    private var proteinList = proteinResources.keys.toMutableList()

    private val dairyRepository = DairyRepository()
    private var dairyResources = dairyRepository.fetchData()
    private var dairyList = dairyResources.keys.toMutableList()

    private val sweetsRepository = SweetsRepository()
    private var sweetsResources = sweetsRepository.fetchData()
    private var sweetsList = sweetsResources.keys.toMutableList()

    private val othersRepository = OthersRepository()
    private var othersResources = othersRepository.fetchData()
    private var othersList = othersResources.keys.toMutableList()

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentRvBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: IngredientRowAdapter

    private var foodGroupCategory: String = "Unitialized"

    companion object {
        fun newInstance(foodGroup: String): AddIngredientFragment {
            return AddIngredientFragment(foodGroup)
        }
    }

    init {
        foodGroupCategory = foodGroup
    }

    private fun setDisplayHomeAsUpEnabled(value : Boolean) {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRvBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(javaClass.simpleName, "onViewCreated")

        val main = activity as MainActivity?
        val actionBarBinding = main?.actionBarBinding
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Add " + foodGroupCategory

        binding.recyclerView.itemAnimator = null

        adapter = IngredientRowAdapter(viewModel, binding.recyclerView.context)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context)
        binding.recyclerView.adapter = adapter
        if(foodGroupCategory == "Fruits")
            adapter.submitList(fruitResources.values.toList().sortedBy { it.nameOfIngredient })
        else if(foodGroupCategory == "Vegetables")
            adapter.submitList(vegetableResources.values.toList().sortedBy { it.nameOfIngredient })
        else if(foodGroupCategory == "Grains")
            adapter.submitList(grainsResources.values.toList().sortedBy { it.nameOfIngredient })
        else if(foodGroupCategory == "Proteins")
            adapter.submitList(proteinResources.values.toList().sortedBy { it.nameOfIngredient })
        else if(foodGroupCategory == "Dairy")
            adapter.submitList(dairyResources.values.toList().sortedBy { it.nameOfIngredient })
        else if(foodGroupCategory == "Sweets")
            adapter.submitList(sweetsResources.values.toList().sortedBy { it.nameOfIngredient })
        else if(foodGroupCategory == "Others")
            adapter.submitList(othersResources.values.toList().sortedBy { it.nameOfIngredient })

        binding.swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.fetchDone.observe(viewLifecycleOwner) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })


        // Add to menu
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Menu is already inflated by main activity
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle action bar item clicks here.
                requireActivity().onBackPressed() // Handles when user click the Back arrow button on the actionbar in Favorites

                return when (menuItem.itemId) {
                    android.R.id.home -> false // Handle in fragment
                    else -> true
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        setDisplayHomeAsUpEnabled(false)
    }

}