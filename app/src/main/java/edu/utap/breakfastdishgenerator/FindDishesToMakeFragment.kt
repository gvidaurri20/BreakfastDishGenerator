package edu.utap.breakfastdishgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        binding.findDishesButton.setOnClickListener {
            //findNavController().navigate(R.id.action_FindDishesToMake_to_UserHomepage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}