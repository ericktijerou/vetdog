package erick.mobile.presentation.navigation

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import erick.mobile.presentation.dog.detail.DogActivity

class Navigator {

    companion object {
        private val EXTRA_DOG = "${DogActivity::class.java.`package`.name}.extra.DOG"
    }

    fun navigateToDog(activity: Activity, dog: String, sharedViews: Array<Pair<View, String>>) {
        val intent = Intent(activity, DogActivity::class.java)
        intent.putExtra(EXTRA_DOG, dog)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *sharedViews).toBundle()
        ActivityCompat.startActivity(activity, intent, options)
    }

    fun getDog(activity: Activity) = activity.intent.getStringExtra(EXTRA_DOG)
}