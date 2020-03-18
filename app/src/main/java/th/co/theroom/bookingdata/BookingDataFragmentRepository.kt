package th.co.theroom.bookingdata

import th.co.theroom.model.BookingEntity

interface BookingDataFragmentRepository {
    suspend fun selectBookingList(): MutableList<BookingEntity>
}