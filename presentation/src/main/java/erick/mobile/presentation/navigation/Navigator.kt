package erick.mobile.presentation.navigation

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import erick.mobile.presentation.dog.detail.DogActivity
import erick.mobile.presentation.dog.list.MainActivity

class Navigator {

    companion object {
        private val EXTRA_DOG = "${DogActivity::class.java.`package`.name}.extra.DOG"
        private val EXTRA_REFRESH = "${MainActivity::class.java.`package`.name}.extra.REFRESH"
    }

    fun navigateToDog(activity: Activity, dog: String, sharedViews: Array<Pair<View, String>>) {
        val intent = Intent(activity, DogActivity::class.java)
        intent.putExtra(EXTRA_DOG, dog)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *sharedViews).toBundle()
        ActivityCompat.startActivity(activity, intent, options)
    }

    fun navigateToHome(activity: Activity, refresh: Boolean) {
        activity.finish()
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra(EXTRA_REFRESH, refresh)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }

    fun getDog(activity: Activity) = activity.intent.getStringExtra(EXTRA_DOG)
    fun getRefresh(activity: Activity) = activity.intent.getBooleanExtra(EXTRA_REFRESH, false)
}