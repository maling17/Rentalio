package com.example.rentalio.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.applandeo.materialcalendarview.utils.DateUtils
import com.example.rentalio.Preferences
import com.example.rentalio.databinding.CalendarFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment : Fragment() {

    var _binding: CalendarFragmentBinding? = null
    val binding get() = _binding!!
    lateinit var preferences: Preferences

    companion object {
        fun newInstance() = CalendarFragment()
    }

    private lateinit var viewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalendarFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = Preferences(requireContext())
        val min = Calendar.getInstance()
        min.add(Calendar.DAY_OF_MONTH, -1)

        binding.ivCancel.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvClear.setOnClickListener { binding.calendarViewSesi1.selectedDates.clear() }
        binding.calendarViewSesi1.setMinimumDate(min)
        binding.calendarViewSesi1.setDisabledDays(getDisabledDays())



        binding.btnSave.setOnClickListener {

            lifecycleScope.launch {

                binding.pbSave.visibility = View.VISIBLE
                binding.btnSave.visibility = View.INVISIBLE

                val myFormat = "MMMM dd" // format tanggal
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

                val formatDayMonthYear = "dd MMMM yyyy" // format tanggal
                val sdfDayMonthYear = SimpleDateFormat(formatDayMonthYear, Locale.getDefault())

                val size = binding.calendarViewSesi1.selectedDates.size
                val pickUpDate = binding.calendarViewSesi1.selectedDates[0].time
                val returnDate = binding.calendarViewSesi1.selectedDates[size - 1].time

                preferences.setValues("from", sdfDayMonthYear.format(pickUpDate))
                preferences.setValues("to", sdfDayMonthYear.format(returnDate))
                preferences.setValues("size", size.toString())

                binding.tvPickup.text = sdf.format(pickUpDate)
                binding.tvReturn.text = sdf.format(returnDate)

                delay(2000)
                binding.pbSave.visibility = View.INVISIBLE
                binding.btnSave.visibility = View.VISIBLE
                requireActivity().onBackPressed()

            }

        }

    }

    private fun getDisabledDays(): List<Calendar> {
        val firstDisabled = DateUtils.getCalendar()
        firstDisabled.add(Calendar.DAY_OF_MONTH, 4)
        val secondDisabled = DateUtils.getCalendar()
        secondDisabled.add(Calendar.DAY_OF_MONTH, 8)
        val thirdDisabled = DateUtils.getCalendar()
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 10)
        val calendars: MutableList<Calendar> = ArrayList()
        calendars.add(firstDisabled)
        calendars.add(secondDisabled)
        calendars.add(thirdDisabled)
        return calendars
    }

}