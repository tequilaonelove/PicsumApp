package ru.test.app.picsum.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.test.app.picsum.core.network.model.PicsDto
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DiffCallback {

    fun <T : List<PicsDto>, R : RecyclerView.ViewHolder> autoNotifyDelegate(
        adapter: RecyclerView.Adapter<R>,
        initialValue: T
    ): ReadWriteProperty<Any?, T> =
        object : ObservableProperty<T>(initialValue) {
            override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
                adapter.autoNotify(oldValue, newValue)
            }
        }

    private fun <T : RecyclerView.ViewHolder> RecyclerView.Adapter<T>.autoNotify(
        oldList: List<PicsDto>, newList: List<PicsDto>
    ) {

        val diffItemCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition].id == newList[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition].imageUrl == newList[newItemPosition].imageUrl

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        }

        DiffUtil.calculateDiff(diffItemCallback).dispatchUpdatesTo(this)
    }
}