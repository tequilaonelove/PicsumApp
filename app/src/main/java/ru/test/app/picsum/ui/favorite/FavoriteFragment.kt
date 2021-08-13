package ru.test.app.picsum.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.test.app.picsum.R
import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.databinding.FragmentFavoriteBinding
import ru.test.app.picsum.ui.base.BaseFragment
import ru.test.app.picsum.ui.adapters.PicsLikedAdapter
import ru.test.app.picsum.ui.base.hide
import ru.test.app.picsum.ui.base.show
import ru.test.app.picsum.ui.listeners.OnActionListener
import ru.test.app.picsum.viewmodel.FavoriteViewModel
import ru.test.app.picsum.viewmodel.PicsComponent
import ru.test.app.picsum.viewmodel.state.Status

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val component by lazy { PicsComponent.create() }
    private val viewModel: FavoriteViewModel by viewModels { component.viewModelFactory() }
    private val adapter by lazy {
        PicsLikedAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvFavorite.adapter = adapter

        lifecycleScope.launch(exceptionHandler) {
            viewModel.likedPicsFlow().onEach { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        res.data?.let {
                            adapter.setItems(it)
                        }
                        binding.progressBar.hide()
                    }
                    Status.ERROR -> {
                        startToast(res.error?.message)
                    }
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }
                }
            }.collect()
        }

        adapter.bindListeners(object : OnActionListener {
            override fun onActionClick(picsDto: PicsDto) {
                viewModel.deleteItem(picsDto)
                viewModel.fetchData()
            }
        })

    }

}