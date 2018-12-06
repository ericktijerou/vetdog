package erick.mobile.presentation.dog.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.View
import dagger.android.support.DaggerAppCompatActivity
import erick.mobile.presentation.R
import erick.mobile.presentation.databinding.ActivityLoginBinding
import erick.mobile.presentation.dog.list.adapter.DogListAdapter
import erick.mobile.presentation.dog.list.model.DogModel
import erick.mobile.presentation.internal.util.lazyThreadSafetyNone
import erick.mobile.presentation.navigation.Navigator
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), DogListAdapter.Callbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityLoginBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(DogListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binder.toolbar)

        binder.viewModel = viewModel
        binder.dogCallbacks = this

        viewModel.loadDogList("med")
    }

    override fun onItemClick(view: View, item: DogModel) {
        val cardView = view.findViewById<View>(R.id.cardview)
        val imageView = view.findViewById<View>(R.id.image_thumbnail)
        val nameView = view.findViewById<View>(R.id.text_name)
        val sharedViews = arrayOf(
                Pair(cardView, ViewCompat.getTransitionName(cardView)),
                Pair(imageView, ViewCompat.getTransitionName(imageView)),
                Pair(nameView, ViewCompat.getTransitionName(nameView)))
        this.let { navigator.navigateToDog(it, item.id!!, sharedViews) }
    }
}
