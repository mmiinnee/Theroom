package th.co.theroom.bookingdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_booking_data.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import th.co.theroom.R
import th.co.theroom.alert.AlertMessageDialogFragment
import th.co.theroom.base.AppConfig
import th.co.theroom.base.Constants
import th.co.theroom.shareviewmodel.ShareViewModel

class BookingDataFragment : Fragment() {

    private val vm: BookingDataFragmentViewModel by viewModel()
    private val vmShare: ShareViewModel by sharedViewModel()

    private lateinit var bookingDataAdapter: BookingDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_booking_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setAdapter()

        vm.getBookingList()

        observeData()

        btn_back.setOnClickListener {
            vmShare.changeTitleBar("ระบบจองห้องพัก")
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeData() {
        vm.getBookingListLiveData.observe(viewLifecycleOwner, Observer {
            bookingDataAdapter.setListData(it)
        })

        vm.errorLiveData.observe(viewLifecycleOwner, Observer {
            AlertMessageDialogFragment.Builder()
                    .setMessage(it)
                    .build()
                    .show(activity?.supportFragmentManager!!, AppConfig.TAG)
        })
    }

    private fun setAdapter() {
        bookingDataAdapter = BookingDataAdapter { bookingEntity ->
            vmShare.changeFragment(Constants.BOOKING_DETAIL)
            vmShare.sendBookingDetailFragment(bookingEntity)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = bookingDataAdapter
        }
    }

    companion object {
        fun newInstance(): BookingDataFragment {
            return BookingDataFragment()
        }
    }
}
