package th.co.theroom.booking

import th.co.theroom.model.BookingEntity
import th.co.theroom.model.RoomEntity
import th.co.theroom.room.TheRoomDao

class BookingFragmentRepositoryImpl(private val theRoomDao: TheRoomDao) : BookingFragmentRepository {

    override suspend fun selectRoomByBedType(buildingNumber: String, bedType: String): MutableList<RoomEntity> = theRoomDao.selectRoomByBedType(buildingNumber, bedType)

    override suspend fun selectRoomByPeopleSize(buildingNumber: String, peopleSize: String): MutableList<RoomEntity> = theRoomDao.selectRoomByPeopleSize(buildingNumber, peopleSize)

    override suspend fun insertBookingRoomData(bookingEntity: BookingEntity) {
        theRoomDao.insertBookingRoomData(bookingEntity)
    }

    override suspend fun updateStatusRoom(buildingNumber: String, roomNumber: String, status: Boolean) {
        theRoomDao.updateStatusRoom(buildingNumber, roomNumber, status)
    }
}