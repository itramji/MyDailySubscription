package com.bornbytes.mydailysubscription.ui.order

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bornbytes.mydailysubscription.R
import com.bornbytes.mydailysubscription.api.BaseApi
import com.bornbytes.mydailysubscription.databinding.OrderFragmentBinding
import com.bornbytes.mydailysubscription.repository.OrderRepository
import com.bornbytes.mydailysubscription.ui.base.BaseFragment
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.model.HorizontalCalendarConfig.DEFAULT_FORMAT_TEXT_TOP
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import kotlinx.android.synthetic.main.layout_appbar.*
import java.util.*

class OrderFragment : BaseFragment<OrderViewModel, OrderFragmentBinding, OrderRepository>() {

    lateinit var horizontalCalendar: HorizontalCalendar

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        initCalenderView()

        backBtn.setOnClickListener { requireActivity().finish() }
        reInitBtn.setOnClickListener { horizontalCalendar.goToday(true) }
    }

    fun initCalenderView() {
        val startDate: Calendar = Calendar.getInstance()
        startDate.add(Calendar.DATE, -20)

        val endDate: Calendar = Calendar.getInstance()
        endDate.add(Calendar.DATE, 20)

        horizontalCalendar = HorizontalCalendar.Builder(view, R.id.calendarView).range(startDate, endDate).datesNumberOnScreen(7).build();

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) {
                tvTitle.text = DateFormat.format(DEFAULT_FORMAT_TEXT_TOP, date)
            }
        };

        horizontalCalendar.goToday(true)
    }

    override fun getViewModelClass() = OrderViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        OrderFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = OrderRepository(remoteDataSource.buildApi(BaseApi::class.java))
}