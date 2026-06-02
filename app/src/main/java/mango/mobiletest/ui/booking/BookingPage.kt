package mango.mobiletest.ui.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mango.mobiletest.data.model.BookingData
import mango.mobiletest.ui.theme.Purple80
import mango.mobiletest.util.formatDate
import mango.mobiletest.util.formatDuration

/**
 * @author: Mango
 * @date: 2026/5/31
 * @description:
 */
@Composable
fun BookingPage(viewModel: BookingViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("BookingInfo", modifier = Modifier.padding(top = 30.dp), fontSize = 16.sp)
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState.value) {
                is BookingUiState.Error -> Text((uiState.value as BookingUiState.Error).message)

                is BookingUiState.Loaded -> {
                    val bookingData = (uiState.value as BookingUiState.Loaded).bookingData
                    BookingInfoView(
                        Modifier
                            .padding(top = 50.dp)
                            .fillMaxWidth(), bookingData
                    ) {
                        viewModel.loadData(forceRefresh = true)
                    }
                }

                BookingUiState.Loading -> CircularProgressIndicator(color = Color.Gray)
            }
        }
    }
}

@Composable
private fun BookingInfoView(modifier: Modifier, bookingData: BookingData, refresh: () -> Unit) {
    Column(
        modifier = modifier
    ) {
        Text("Duration:${bookingData.duration.formatDuration()}")
        Text("Expiry:${bookingData.expiryTime.formatDate()}")
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
            Text("Booking Detail:")
            Button(onClick = refresh)
            { Text("refresh") }
        }
        LazyColumn(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(bookingData.segments) { detail ->
                SegmentCard(detail)
            }
        }
    }
}

@Composable
private fun SegmentCard(detail: BookingData.BookingDetail) {
    val originAndDestinationPair = detail.originAndDestinationPair
    Row(
        modifier = Modifier
            .background(Purple80, shape = RoundedCornerShape(15.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(modifier = Modifier.padding(10.dp).weight(0.5f)) {
            Text("Origin:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(originAndDestinationPair.destinationCity)
            Text("Name: ", fontSize = 13.sp)
            Text(originAndDestinationPair.origin.displayName, minLines = 2)
            Text("Code: ", fontSize = 13.sp)
            Text(originAndDestinationPair.origin.code)
        }

        Column(modifier = Modifier.padding(10.dp).weight(0.5f)) {
            Text("Destination: ",fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(originAndDestinationPair.destinationCity)
            Text("Name:", fontSize = 13.sp)
            Text(originAndDestinationPair.destination.displayName, minLines = 2)
            Text("Code:" , fontSize = 13.sp)
            Text(originAndDestinationPair.destination.code)

        }
    }
}