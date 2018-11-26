package erick.mobile.presentation.dog.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.android.support.DaggerAppCompatActivity
import erick.mobile.presentation.R
import erick.mobile.presentation.databinding.ActivityLoginBinding
import erick.mobile.presentation.dog.list.adapter.DogListAdapter
import erick.mobile.presentation.dog.list.model.DogModel
import erick.mobile.presentation.internal.util.lazyThreadSafetyNone
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), DogListAdapter.Callbacks, View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        binder.fabClick = this

        viewModel.loadEventList("med")
    }

    override fun onItemClick(view: View, item: DogModel) {

    }

    override fun onClick(p0: View?) {

    }
}
