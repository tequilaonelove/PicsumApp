package ru.test.app.picsum.ui.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import ru.test.app.picsum.R
import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.databinding.FragmentExploreBinding
import ru.test.app.picsum.ui.adapters.PicsAdapter
import ru.test.app.picsum.ui.base.BaseFragment
import ru.test.app.picsum.ui.base.hide
import ru.test.app.picsum.ui.base.show
import ru.test.app.picsum.ui.listeners.OnActionListener
import ru.test.app.picsum.ui.listeners.OnLoadMoreListener
import ru.test.app.picsum.viewmodel.PicsComponent
import ru.test.app.picsum.viewmodel.ExploreViewModel
import ru.test.app.picsum.viewmodel.state.Status
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExploreFragment : BaseFragment(R.layout.fragment_explore) {

    private val binding by viewBinding(FragmentExploreBinding::bind)
    private val component by lazy { PicsComponent.create() }
    private val viewModel: ExploreViewModel by viewModels { component.viewModelFactory() }
    private val adapter by lazy {
        PicsAdapter(binding.rvExplore)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvExplore.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvExplore.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.picsFlow().onEach { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        res.data?.let {
                            adapter.setItems(it)
                        }
                        binding.progressBar.hide()
                        adapter.setLoaded()
                    }
                    Status.ERROR -> {
                        when (res.error) {
                            is UnknownHostException,
                            is SocketTimeoutException -> {
                                startToast(getString(R.string.network_error))
                            }
                            else -> {
                                startToast(res.error?.message.toString())
                            }
                        }
                        adapter.setLoaded()
                    }
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }
                }
            }.collect()
        }

        adapter.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                viewModel.loadMorePics()
            }

        })

        adapter.bindListeners(object : OnActionListener {
            override fun onActionClick(picsDto: PicsDto) {
                viewModel.likeItem(picsDto)
            }
        })

    }

}