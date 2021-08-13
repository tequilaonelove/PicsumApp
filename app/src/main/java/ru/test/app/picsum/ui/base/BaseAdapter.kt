package ru.test.app.picsum.ui.base

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

private typealias ViewHolderViewBindingInflater<VB> = (
    inflater: LayoutInflater,
    parent: ViewGroup,
    attachToParent: Boolean
) -> VB

abstract class BaseAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    fun <VB : ViewBinding> ViewGroup.inflateBinding(
        bindingInflater: ViewHolderViewBindingInflater<VB>
    ): VB {
        return bindingInflater.invoke(LayoutInflater.from(context), this, false)
    }

    abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    open fun BaseViewHolder.toSquareTransform(): BaseViewHolder = this
}