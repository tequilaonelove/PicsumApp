package ru.test.app.picsum.ui.adapters

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.test.app.picsum.R
import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.databinding.ItemExploreBinding
import ru.test.app.picsum.ui.base.BaseAdapter
import ru.test.app.picsum.ui.listeners.OnActionListener
import ru.test.app.picsum.ui.listeners.OnLoadMoreListener
import ru.test.app.picsum.utils.DiffCallback

class PicsAdapter(recyclerView: RecyclerView) : BaseAdapter<RecyclerView.ViewHolder>() {

    private var picsList by DiffCallback().autoNotifyDelegate(this, listOf())
    private var isLoading = false
    private var visibleThreshold = 5
    private var lastVisibleItem = 0
    private var totalItemCount = 0
    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var onActionListener: OnActionListener? = null

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayoutManager!!.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener!!.onLoadMore()
                    }
                    isLoading = true
                }
            }
        })
    }

    inner class ViewHolder(private val binding: ItemExploreBinding) : BaseViewHolder(binding) {
        fun bind(picsItem: PicsDto) {
            with(binding) {
                author.text = picsItem.author
                Glide.with(picture.context)
                    .load(picsItem.imageUrl)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.default_image)
                    .fallback(R.drawable.default_image)
                    .error(R.drawable.default_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(600))
                    .into(picture)
            }
        }

        fun bindListeners() {
            itemView.setOnClickListener {
                onActionListener?.onActionClick(picsList[layoutPosition])
                Toast.makeText(it.context, it.context.getString(R.string.pics_liked), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = ViewHolder(parent.inflateBinding(ItemExploreBinding::inflate))
        viewHolder.bindListeners()
        return viewHolder.toSquareTransform()
    }

    override fun BaseViewHolder.toSquareTransform(): BaseViewHolder {
        val view = itemView.findViewById<ImageView>(R.id.picture)
        view.layoutParams.height = (Resources.getSystem().displayMetrics.widthPixels * 96) / 100
        return this
    }

    override fun getItemCount(): Int = picsList.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(picsList[position])
    }

    fun setItems(items: List<PicsDto>) {
        picsList = items
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    fun bindListeners(listener: OnActionListener) {
        this.onActionListener = listener
    }

    fun setLoaded() {
        isLoading = false
    }

}