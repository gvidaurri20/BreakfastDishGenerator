package edu.utap.breakfastdishgenerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import edu.utap.breakfastdishgenerator.MainActivity
import edu.utap.breakfastdishgenerator.R
import edu.utap.breakfastdishgenerator.databinding.ActionBarBinding
import edu.utap.breakfastdishgenerator.databinding.AppLaunchScreenBinding

class AppLaunchScreenFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: AppLaunchScreenBinding? = null
    private val binding get() = _binding!!
    var actionBarBinding: ActionBarBinding? = null

    companion object {
        fun newInstance(): AppLaunchScreenFragment {
            return AppLaunchScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AppLaunchScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = activity as MainActivity?

        val actionBarBinding = main?.actionBarBinding
        actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Breakfast Dish Generator"
        actionBarBinding?.actionGoHome?.visibility = View.GONE
        actionBarBinding?.actionGoToFavorites?.visibility = View.GONE

        binding.signInButton.setOnClickListener {
            if (main != null) {
                main.clickSignInButton()
            }

            parentFragmentManager.commit {
                replace(
                    R.id.main_frame, UserHomepageFragment.newInstance(),
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