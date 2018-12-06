package erick.mobile.presentation.dog.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.view.ViewGroup
import androidx.view.doOnPreDraw
import dagger.android.support.DaggerAppCompatActivity
import erick.mobile.presentation.R
import erick.mobile.presentation.databinding.ActivityDogBinding
import erick.mobile.presentation.internal.util.lazyThreadSafetyNone
import erick.mobile.presentation.navigation.Navigator
import javax.inject.Inject

class DogActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityDogBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_dog)
    }

    private val eventDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(DogDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        setSupportActionBar(binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binder.dogDetailViewModel = eventDetailViewModel

        val event = navigator.getDog(this)
        eventDetailViewModel.loadEventDetail(event)

        eventDetailViewModel.dog.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
    }

    override fun getParentActivityIntent(): Intent {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
}