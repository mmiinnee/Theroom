package th.co.theroom.bookingdata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_list_item.view.*
import th.co.theroom.R
import th.co.theroom.model.BookingEntity

class BookingDataAdapter(private val callBack: (BookingEntity) -> Unit) : RecyclerView.Adapter<BookingDataAdapter.ViewHolder>() {

    private var mItems: MutableList<BookingEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mItems[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: BookingEntity) {
            itemView.tv_name_last.text = "ชื่อ-นามสกุล : ${item.bookingName}"

            itemView.tv_building.text = "ตึก : ${item.buildingNumber}"

            itemView.tv_roomNumber.text = "ห้อง : ${item.roomNumber}"

            itemView.ln_item.setOnClickListener { callBack.invoke(item) }
        }
    }

    fun setListData(response: MutableList<BookingEntity>) {
        mItems.clear()
        mItems.addAll(response)
        notifyDataSetChanged()
    }
}