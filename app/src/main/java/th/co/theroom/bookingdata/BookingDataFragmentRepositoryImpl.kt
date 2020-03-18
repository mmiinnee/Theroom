package th.co.theroom.bookingdata

import th.co.theroom.model.BookingEntity
import th.co.theroom.room.TheRoomDao

class BookingDataFragmentRepositoryImpl(private val theRoomDao: TheRoomDao) : BookingDataFragmentRepository {
    override suspend fun selectBookingList(): MutableList<BookingEntity> = theRoomDao.selectBookingList()
}