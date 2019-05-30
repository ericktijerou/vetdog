package erick.mobile.presentation.dog.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import androidx.view.doOnPreDraw
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import dagger.android.support.DaggerAppCompatActivity
import erick.mobile.presentation.R
import erick.mobile.presentation.databinding.ActivityDogBinding
import erick.mobile.presentation.internal.util.lazyThreadSafetyNone
import erick.mobile.presentation.navigation.Navigator
import kotlinx.android.synthetic.main.content_dog.*
import javax.inject.Inject

class DogActivity : DaggerAppCompatActivity() {

    companion object {
        private val DYNAMIC_LINK_DOMAIN = "vetdog.page.link"
        private val QUERY_PARAM_DOG = "dogId"
        private var fromLink : Boolean = false
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityDogBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_dog)
    }

    private val dogDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(DogDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        setSupportActionBar(binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binder.dogDetailViewModel = dogDetailViewModel

        FirebaseDynamicLinks.getInstance().getDynamicLink(intent).addOnFailureListener {
            // error
        }.addOnSuccessListener {
            // deep link
            val dogId: String
            if (it == null) {
                dogId = navigator.getDog(this)
                fromLink = false
            } else {
                dogId = it.link.getQueryParameter(QUERY_PARAM_DOG)
                fromLink = true
            }
            dogDetailViewModel.loadDogDetail(dogId)
            fbShare.setOnClickListener {
                share(dogId)
            }
        }



        dogDetailViewModel.dog.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (fromLink) {
            navigator.navigateToHome(this, fromLink)
        } else {
            super.onBackPressed()
        }
    }
    override fun getParentActivityIntent(): Intent {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    private fun share(id: String) {
        val myUri = createShareUri(id)
        Log.d("Shared Link", myUri.toString())

        val dynamicLinkUri = createDynamicUri(myUri)
        Log.d("Dynamic Link", dynamicLinkUri.toString())

        shortenLink(dynamicLinkUri)
    }

    private fun createShareUri(saladId: String): Uri {
        val builder = Uri.Builder()
        builder.scheme(getString(R.string.config_scheme))
            .authority(getString(R.string.config_host))
            .appendPath(getString(R.string.config_path_dogs))
            .appendQueryParameter(QUERY_PARAM_DOG, saladId)
        return builder.build()
    }

    private fun shortenLink(linkUri: Uri) {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLongLink(linkUri)
            .buildShortDynamicLink()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val shortLink = task.result?.shortLink
                    val msg = "Hey, check out this nutritious salad I found: $shortLink"
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT, msg)
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                } else {
                    //Timber.e(task.exception)
                }
            }
    }

    private fun createDynamicUri(myUri: Uri): Uri {
        val dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(myUri)
            .setDynamicLinkDomain(DYNAMIC_LINK_DOMAIN)
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder()
                    .build()
            )
//                .setIosParameters(DynamicLink.IosParameters.Builder("ibi").setFallbackUrl()build())
            .buildDynamicLink()
        return dynamicLink.uri
    }
}