package mango.mobiletest.ui.booking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mango.mobiletest.data.repository.BookingRepository

/**
 * @author: Mango
 * @date: 2026/5/31
 * @description:
 */
class BookingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookingRepository = BookingRepository(application.applicationContext)
    private val _uiState: MutableStateFlow<BookingUiState> =
        MutableStateFlow(BookingUiState.Loading)
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = BookingUiState.Loading
            try {
                delay(1500)
                val bookingData = repository.loadBookingData(forceRefresh)

                _uiState.value = if (bookingData == null) BookingUiState.Error(message = "no data")
                else BookingUiState.Loaded(bookingData)
            } catch (e: Exception) {
                _uiState.value = BookingUiState.Error(e.message ?: "error")
            }
        }
    }

}