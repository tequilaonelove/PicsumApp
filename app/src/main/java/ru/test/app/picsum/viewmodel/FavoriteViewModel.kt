package ru.test.app.picsum.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.repository.PicsRepositoryImpl
import ru.test.app.picsum.viewmodel.base.BaseViewModel
import ru.test.app.picsum.viewmodel.state.Resource
import timber.log.Timber
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val picsRepository: PicsRepositoryImpl
) : BaseViewModel() {

    private val _likedPicsFlow = MutableStateFlow<Resource<List<PicsDto>>>(resourceLoading(null))

    fun likedPicsFlow(): StateFlow<Resource<List<PicsDto>>> {
        return _likedPicsFlow.asStateFlow()
    }

    init {
        fetchData()
    }

    fun fetchData() {
        _likedPicsFlow.value = resourceLoading(listOfNotNull())
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val data = picsRepository.likedPics()
                _likedPicsFlow.value = resourceSuccess(data)
            } catch (e: Exception) {
                _likedPicsFlow.value = resourceError(e, null)
            }
        }
    }

    fun deleteItem(picsDto: PicsDto) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                withContext(currentCoroutineContext()) {
                    picsRepository.deleteItem(picsDto)
                }
                val data = picsRepository.likedPics()
                _likedPicsFlow.value = resourceSuccess(data)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}