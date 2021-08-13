package ru.test.app.picsum.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.repository.PicsRepositoryImpl
import ru.test.app.picsum.viewmodel.base.BaseViewModel
import ru.test.app.picsum.viewmodel.state.Resource
import timber.log.Timber
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val picsRepository: PicsRepositoryImpl
) : BaseViewModel() {

    private var page = 1
    private val _picsflow = MutableStateFlow<Resource<List<PicsDto>>>(resourceLoading(listOf()))

    fun picsFlow(): StateFlow<Resource<List<PicsDto>>> {
        return _picsflow.asStateFlow()
    }

    init {
        initialLoadingPics()
    }

    private fun initialLoadingPics() {
        _picsflow.value = resourceLoading(listOfNotNull())
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val data = picsRepository.pics(page)
                _picsflow.value = resourceSuccess(data)
            } catch (e: Exception) {
                _picsflow.value = resourceError(e, listOf())
            }
        }
    }

    fun loadMorePics() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                increasePage()
                val data = picsRepository.loadMore(page)
                _picsflow.value = resourceSuccess(data)
            } catch (e: Exception) {
                reducePage()
                _picsflow.value = resourceError(e, listOf())
            }
        }
    }

    fun likeItem(picsDto: PicsDto) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                picsRepository.addToLikedItem(picsDto)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun increasePage() {
        page += 1
        Timber.d("increase page is $page")
    }

    private fun reducePage() {
        page -= 1
        Timber.d("reduce page is $page")
    }
}

