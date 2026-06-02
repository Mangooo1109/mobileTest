package mango.mobiletest.data.model


data class BookingData(
    //"shipReference": "ABCDEF",
//"shipToken": "AAAABBBCCCCDDD",
//"canIssueTicketChecking": false,
//"expiryTime": "1722409261",
//"duration": 2430,
//"segments":
    val shipReference: String,
    val shipToken: String,
    val canIssueTicketChecking: Boolean,
    val expiryTime: String,
    val duration: Long,
    val segments: List<BookingDetail> = emptyList()
) {
    //{
    //    "id": 1,
    //    "originAndDestinationPair":
    //    {
    //        "destination":
    //        {
    //            "code": "BBB",
    //            "displayName": "BBB DisplayName",
    //            "url": "www.ship.com"
    //        },
    //        "destinationCity": "BBB City",
    //        "origin":
    //        {
    //            "code": "AAA",
    //            "displayName": "AAA DisplayName",
    //            "url": "www.ship.com"
    //        },
    //        "originCity": "AAA City"
    //    }
    //}
    data class BookingDetail(
        val id: Int,
        val originAndDestinationPair: OriginAndDestinationPair
    ) {
        data class OriginAndDestinationPair(
            val destination: AddressInfo,
            val destinationCity: String,
            val origin: AddressInfo,
            val originCity: String
        ) {
            data class AddressInfo(
                val code: String,
                val displayName: String,
                val url: String
            )
        }
    }
}

