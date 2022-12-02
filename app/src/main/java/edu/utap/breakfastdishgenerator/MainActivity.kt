package edu.utap.breakfastdishgenerator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import edu.utap.breakfastdishgenerator.auth.AuthInit
import edu.utap.breakfastdishgenerator.databinding.ActionBarBinding
import edu.utap.breakfastdishgenerator.databinding.ActivityMainBinding
import edu.utap.breakfastdishgenerator.ui.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val mainFragTag = "userHomepageFragTag"
    }

    val viewModel: MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    var actionBarBinding: ActionBarBinding? = null

    private fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        actionBarBinding = ActionBarBinding.inflate(layoutInflater)
        // Apply the custom view
        actionBar.customView = actionBarBinding?.root
    }

    private fun addAppLaunchScreenFragment() {
        // No back stack for home
        supportFragmentManager.commit {
            add(R.id.main_frame, AppLaunchScreenFragment.newInstance(), mainFragTag)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
        }
    }

    fun actionBarLaunchUserHomepageFragment() {
        // XXX Write me actionBarBinding
        actionBarBinding?.actionGoHome?.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            supportFragmentManager.commit {
                replace(R.id.main_frame, UserHomepageFragment.newInstance(), mainFragTag)
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
    }

    fun actionBarLaunchFavoritesFragment() {
        actionBarBinding?.actionGoToFavorites?.setOnClickListener {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 1
            supportFragmentManager.commit {
                replace(R.id.main_frame, FavoritesFragment.newInstance(), "favoriteDishesFrag")
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
    }

    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
            viewModel.updateUser()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }

        addAppLaunchScreenFragment()
        actionBarLaunchUserHomepageFragment()
        actionBarLaunchFavoritesFragment()

    }

    fun clickSignInButton() {
        AuthInit(viewModel, signInLauncher)
    }


    // This function executes when the user presses the actual Back button from the Android device
    override fun onBackPressed() {
        if (viewModel.whichDishesFragmentUserIsCurrentlyViewing == 1) {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 0
            supportFragmentManager.popBackStack()
        }
        else {
            viewModel.whichDishesFragmentUserIsCurrentlyViewing = 1
            supportFragmentManager.popBackStack()
        }
    }
}