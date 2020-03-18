package th.co.theroom.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import th.co.theroom.model.BookingEntity
import th.co.theroom.model.RoomEntity

@Dao
interface TheRoomDao {

    @Insert
    suspend fun insertRoomData(roomEntity: RoomEntity)

    @Query("SELECT * FROM Room WHERE buildingNumber = :buildingNumber AND bedType = :bedType AND status = :status")
    suspend fun selectRoomByBedType(buildingNumber: String, bedType: String, status: Boolean = true): MutableList<RoomEntity>

    @Query("SELECT * FROM Room WHERE buildingNumber = :buildingNumber AND peopleSize = :peopleSize AND status = :status")
    suspend fun selectRoomByPeopleSize(buildingNumber: String, peopleSize: String, status: Boolean = true): MutableList<RoomEntity>

    @Insert
    suspend fun insertBookingRoomData(bookingEntity: BookingEntity)

    @Query("UPDATE Room SET status = :status WHERE buildingNumber = :buildingNumber AND roomNumber = :roomNumber")
    suspend fun updateStatusRoom(buildingNumber: String, roomNumber: String, status: Boolean)

    @Query("SELECT * FROM Booking")
    suspend fun selectBookingList(): MutableList<BookingEntity>

//    @Query("DELETE FROM CheckIn")
//    suspend fun clearCheckInTable()
//
//    @Insert
//    suspend fun insertEmployee(employeeEntity: EmployeeEntity)
//
//    @Query("SELECT * FROM Employee")
//    suspend fun getEmployeeList(): MutableList<EmployeeEntity>
//
//    @Query("SELECT * FROM Employee WHERE employeeNumber = :number")
//    suspend fun getEmployeeData(number: String): EmployeeEntity
//
//    @Query("UPDATE Employee SET employeeName = :name, employeeLastName = :lastName, employeeAge = :age, employeePhoneNumber = :phoneNumber, employeeaAddress = :address WHERE employeeNumber = :number")
//    suspend fun updateEmployeeData(number: String, name: String, lastName: String, age: String, phoneNumber: String, address: String)
//
//    @Query("DELETE FROM Employee WHERE employeeNumber = :number")
//    suspend fun deleteEmployeeData(number: String)
//
//    @Query("DELETE FROM Employee")
//    suspend fun clearEmployTable()
//
//    @Insert
//    suspend fun insertAccount(accountEntity: AccountEntity)
//
//    @Query("SELECT * FROM Account")
//    suspend fun getAccountList(): MutableList<AccountEntity>
//
//    @Query("DELETE FROM Account WHERE rowid = :rowId")
//    suspend fun deleteAccountData(rowId: Int)
//
//    @Query("DELETE FROM Account")
//    suspend fun clearAccountTable()

}