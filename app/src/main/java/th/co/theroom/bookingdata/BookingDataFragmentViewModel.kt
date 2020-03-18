package th.co.theroom.bookingdata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.co.theroom.base.BaseViewModel
import th.co.theroom.model.BookingEntity
import th.co.theroom.model.Result
import th.co.theroom.usecase.SelectBookingListUseCase

class BookingDataFragmentViewModel(private val selectBookingListUseCase: SelectBookingListUseCase) : BaseViewModel() {

    val getBookingListLiveData: MutableLiveData<MutableList<BookingEntity>> = MutableLiveData()

    fun getBookingList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = selectBookingListUseCase.execute(Unit)) {
                    is Result.Success -> {
                        getBookingListLiveData.postValue(result.data)
                    }
                    is Result.Fail -> {
                        errorLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }
}