package edu.utap.breakfastdishgenerator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import edu.utap.breakfastdishgenerator.databinding.ActionBarBinding
import edu.utap.breakfastdishgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val mainFragTag = "userHomepageFragTag"
        private const val favoritesFragTag = "favoritesFragTag"

        var canLaunchHomepage : Boolean = false // can only launch home page if user is anywhere but home page
        var canLaunchFavorites : Boolean = true // can only launch favorites if user is at home page
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    var actionBarBinding: ActionBarBinding? = null

    // https://stackoverflow.com/questions/24838155/set-onclick-listener-on-action-bar-title-in-android/29823008#29823008
    private fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        actionBarBinding = ActionBarBinding.inflate(layoutInflater)
        // Apply the custom view
        actionBar.customView = actionBarBinding?.root
        /*actionBarBinding!!.actionGoHome.setOnClickListener {
            println("GO HOME!")
            println("supportFragmentManager.backStackEntryCount: " + supportFragmentManager.backStackEntryCount)

            if(canLaunchHomepage == true) {
                println("supportFragmentManager.backStackEntryCount: " + supportFragmentManager.backStackEntryCount)
                //findNavController().navigate(R.id.action_GoBacktoUserHomepage)
            }
        }*/
        /*actionBarBinding!!.actionGoToFavorites.setOnClickListener {
            println("GO TO FAVORITES!")
            //findNavController().navigate(R.id.action_GotoUserFavorites)
        }*/
    }

    private fun addUserHomepageFragment() {
        // No back stack for home
        supportFragmentManager.commit {
            add(R.id.main_frame, UserHomepageFragment.newInstance(), mainFragTag)
            // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
        }
    }

    fun actionBarLaunchUserHomepageFragment() {
        // XXX Write me actionBarBinding
        actionBarBinding?.actionGoHome?.setOnClickListener {
            println("GO HOME!")
            supportFragmentManager.commit {
                replace(R.id.main_frame, UserHomepageFragment.newInstance(), mainFragTag)
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
    }

    fun actionBarLaunchFindDishesToMakeFragment() {
        // XXX Write me actionBarBinding
        actionBarBinding?.actionGoToFavorites?.setOnClickListener {
            println("GO TO FAVORITES!")
            supportFragmentManager.commit {
                replace(R.id.main_frame, FindDishesToMakeFragment.newInstance(), "finddishestomakefragtag")
                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
    }

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
            viewModel.updateUser()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        /*super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }*/

        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }

        /*firstBinding = UserHomepageBinding.inflate(layoutInflater)
        setContentView(firstBinding.root)
        firstBinding.logoutBut.setOnClickListener {
            println("got here")
            // XXX Write me.
            viewModel.signOut()

            *//*binding.userName.text = viewModel.observeDisplayName().value
            binding.userEmail.text = viewModel.observeEmail().value
            binding.userUid.text = viewModel.observeUid().value*//*
        }
        firstBinding.loginBut.setOnClickListener {
            // XXX Write me.
            val user = FirebaseAuth.getInstance().currentUser
            if(user == null) {
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build())

                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                signInLauncher.launch(signInIntent)
            }
        }*/


        /*if(savedInstanceState == null) {
            binding.displayNameET.text.clear()
        }

        // XXX Write me. Set data to display in UI
        viewModel.observeDisplayName().observe(this, Observer { binding.userName.text = viewModel.observeDisplayName().value })
        viewModel.observeEmail().observe(this, Observer { binding.userEmail.text = viewModel.observeEmail().value })
        viewModel.observeUid().observe(this, Observer{ binding.userUid.text = viewModel.observeUid().value })

        binding.logoutBut.setOnClickListener {
            // XXX Write me.
            viewModel.signOut()

            binding.userName.text = viewModel.observeDisplayName().value
            binding.userEmail.text = viewModel.observeEmail().value
            binding.userUid.text = viewModel.observeUid().value
        }
        binding.loginBut.setOnClickListener {
            // XXX Write me.
            val user = FirebaseAuth.getInstance().currentUser
            if(user == null) {
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build())

                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                signInLauncher.launch(signInIntent)
            }
        }
        binding.setDisplayName.setOnClickListener {
            // XXX Write me.
            if(binding.displayNameET.text.toString() != "")
                AuthInit.setDisplayName(binding.displayNameET.text.toString(), viewModel)
            binding.displayNameET.text.clear()
        }*/

        AuthInit(viewModel, signInLauncher)

        // Sets title to "Homepage of username"
        viewModel.observeDisplayName().observe(this, Observer {
            actionBarBinding?.actionBarTitleOfCurrentPage?.text = "Homepage of " + viewModel.observeDisplayName().value
        })

        addUserHomepageFragment()
        actionBarLaunchUserHomepageFragment()
        actionBarLaunchFindDishesToMakeFragment()

    }


    /*private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    */

}