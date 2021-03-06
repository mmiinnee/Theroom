package th.co.theroom.bookingdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_booking_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

import th.co.theroom.R
import th.co.theroom.model.BookingEntity
import th.co.theroom.shareviewmodel.ShareViewModel

class BookingDetailFragment : Fragment() {

    private val vmShare: ShareViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_booking_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        observeData()

        btn_back.setOnClickListener {
            vmShare.changeTitleBar("ระบบจองห้องพัก")
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeData() {
        vmShare.sendDataFragmentLiveData.observe(viewLifecycleOwner, Observer {
            tv_name_last.append(it.bookingName)
            tv_building.append(it.buildingNumber)
            tv_roomNumber.append(it.roomNumber)
            when (it.buildingNumber) {
                "A", "B" -> {
                    tv_bedType.text = "ประเภทห้อง : ${it.bedType}"
                }
                "C" -> {
                    tv_bedType.text = "จำนวนคน : ${it.peopleSize}"
                }
            }
            tv_checkInOut.append("${it.dateCheckIn} - ${it.dateCheckOut}")
        })
    }

    companion object {
        fun newInstance(): BookingDetailFragment {
            return BookingDetailFragment()
        }
    }
}
