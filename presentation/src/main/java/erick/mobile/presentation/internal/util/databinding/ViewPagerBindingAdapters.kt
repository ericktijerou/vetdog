package erick.mobile.presentation.internal.util.databinding

import android.databinding.BindingAdapter
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

object ViewPagerBindingAdapters {

    @JvmStatic
    @BindingAdapter("setupWithViewPager")
    fun setupWithViewPager(tabLayout: TabLayout, viewPager: ViewPager) {
        tabLayout.setupWithViewPager(viewPager)
    }

    @JvmStatic
    @BindingAdapter("pageMargin")
    fun setPageMargin(viewPager: ViewPager, margin: Float) {
        viewPager.pageMargin = margin.toInt()
    }
}
