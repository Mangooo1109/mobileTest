package mango.mobiletest.ui.booking

import mango.mobiletest.data.model.BookingData

/**
 * @author: Mango
 * @date: 2026/5/31
 * @description:
 */
sealed class BookingUiState {
    data object Loading : BookingUiState()
    data class Loaded(val bookingData: BookingData) : BookingUiState()
    data class Error(val message : String) : BookingUiState()
}