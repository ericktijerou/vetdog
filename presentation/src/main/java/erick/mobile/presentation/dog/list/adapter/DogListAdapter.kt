package erick.mobile.presentation.dog.list.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import erick.mobile.presentation.R
import erick.mobile.presentation.databinding.ActivityLoginBinding
import erick.mobile.presentation.databinding.DogListItemBinding
import erick.mobile.presentation.dog.list.model.DogModel

class DogListAdapter(private val items: List<DogModel>, private val callbacks: Callbacks? = null) :
    RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: DogModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: DogListItemBinding = DataBindingUtil.inflate(inflater, R.layout.dog_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.do = items[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: DogListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}
