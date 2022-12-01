package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
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

        binding.viewFavoritesButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, FavoritesFragment.newInstance(),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }

        binding.generateNewDishButton.setOnClickListener {
            viewModel.whichIngredientFragmentUserIsCurrentlyViewing = 0
            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, FindDishesToMakeFragment.newInstance(),
                    MainActivity.mainFragTag
                )
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }

        val main = activity as MainActivity?
        val actionBarBinding = main?.actionBarBinding
        /*val username = viewModel.observeDisplayName().value
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Homepage of " + username*/

        actionBarBinding?.actionGoHome?.visibility = View.VISIBLE
        actionBarBinding?.actionGoToFavorites?.visibility = View.VISIBLE
        // Sets title to "Homepage of username"
        viewModel.observeDisplayName().observe(viewLifecycleOwner, Observer {
            actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Homepage of " + viewModel.observeDisplayName().value
        })

        binding.logoutButton.setOnClickListener {
            viewModel.signOut()

            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, AppLaunchScreenFragment.newInstance(),
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