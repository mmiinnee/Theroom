package th.co.theroom.usecase

import th.co.theroom.booking.BookingFragmentRepositoryImpl
import th.co.theroom.model.BookingEntity
import th.co.theroom.model.Result
import th.co.theroom.model.RoomModel
import th.co.theroom.splashscreen.SplashScreenRepositoryImpl

class UpdateStatusRoomUserCase(private val bookingFragmentRepositoryImpl: BookingFragmentRepositoryImpl) : BaseCoroutinesUseCase<BookingEntity, Boolean>() {
    override suspend fun execute(parameter: BookingEntity): Result<Boolean> {
        return try {
            val response = bookingFragmentRepositoryImpl.updateStatusRoom(parameter.buildingNumber, parameter.roomNumber, false)
            if (response != null) {
                Result.Success(true)
            } else {
                Result.Fail("เกิดข้อผิดพลาดในการจองห้องพัก")
            }
        } catch (e: Exception) {
            return Result.Fail(e.toString())
        }
    }
}