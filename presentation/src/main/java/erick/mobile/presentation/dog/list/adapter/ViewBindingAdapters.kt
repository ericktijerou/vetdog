package erick.mobile.presentation.dog.list.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import erick.mobile.presentation.dog.list.model.DogModel

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("eventAdapter", "eventCallbacks", requireAll = false)
    fun setEventAdapter(recyclerView: RecyclerView, items: List<DogModel>?, callbacks: DogListAdapter.Callbacks?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = DogListAdapter(it, callbacks)
        }
    }
}
