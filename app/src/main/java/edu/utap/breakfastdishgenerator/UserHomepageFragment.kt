package edu.utap.breakfastdishgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.utap.breakfastdishgenerator.databinding.UserHomepageBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserHomepageFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: UserHomepageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): UserHomepageFragment {
            return UserHomepageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = UserHomepageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generateNewDishButton.setOnClickListener {
            //findNavController().navigate(R.id.action_UserHomepage_to_FindDishesToMake)
        }

        val main = activity as MainActivity?
        val actionBarBinding = main?.actionBarBinding
        val username = viewModel.observeDisplayName().value
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Homepage of " + username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}