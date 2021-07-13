package com.example.rentalio.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rentalio.Preferences
import com.example.rentalio.R
import com.example.rentalio.databinding.BookingFragmentBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class BookingFragment : Fragment() {

    var _binding: BookingFragmentBinding? = null
    val binding get() = _binding!!
    lateinit var preferences: Preferences
    var dateFrom: String? = ""
    var dateTo: String? = ""
    var size: Int? = 0

    companion object {
        fun newInstance() = BookingFragment()
    }

    private lateinit var viewModel: BookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookingViewModel::class.java)
        // TODO: Use the ViewModel

        preferences = Preferences(requireContext())

        dateFrom = preferences.getValues("from").toString()
        dateTo = preferences.getValues("to").toString()
        size = preferences.getValues("size")!!.toString().toInt()

        initView()

        binding.ivBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvDateFrom.setOnClickListener {
            findNavController().navigate(R.id.action_bookingFragment_to_calendarFragment)
        }
        binding.tvDateTo.setOnClickListener {
            findNavController().navigate(R.id.action_bookingFragment_to_calendarFragment)
        }
        binding.btnBookNow.setOnClickListener {
            preferences.setValues("from", "dd-mm-yyyy")
            preferences.setValues("to", "dd-mm-yyyy")
            preferences.setValues("size", "0")
            requireActivity().onBackPressed()
            Toast.makeText(requireContext(), "Berhasil Booking", Toast.LENGTH_SHORT).show()
            initView()
        }

        binding.tvWithdriver.setOnClickListener {
            changeBackgroundDriver(binding.tvWithdriver, binding.tvWithoutdriver)
            binding.tvTransmission.visibility = View.GONE
            binding.llTransmission.visibility = View.GONE
        }
        binding.tvWithoutdriver.setOnClickListener {
            changeBackgroundDriver(binding.tvWithoutdriver, binding.tvWithdriver)
            binding.tvTransmission.visibility = View.VISIBLE
            binding.llTransmission.visibility = View.VISIBLE
        }
        binding.llAt.setOnClickListener {
            changeBackgroundTransmission(
                binding.llAt,
                binding.tvAT,
                binding.tvAuto,
                binding.llMt,
                binding.tvMt,
                binding.tvManual
            )
        }

           binding.llMt.setOnClickListener {
            changeBackgroundTransmission(
                binding.llMt,
                binding.tvMt,
                binding.tvManual,
                binding.llAt,
                binding.tvAT,
                binding.tvAuto
            )
        }

        binding.cbTerm.setOnCheckedChangeListener { buttonView, isChecked ->

            if (!isChecked) {
                binding.btnBookNow.isEnabled = false
                binding.btnBookNow.setBackgroundColor(resources.getColor(R.color.grey_font))
            } else {
                binding.btnBookNow.isEnabled = true
                binding.btnBookNow.setBackgroundColor(resources.getColor(R.color.orange))

            }

        }

    }

    private fun setNominal(nominal: Int) {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val poinAwal = formatter.format(nominal)
        val point = poinAwal.replace(",", ".")
        binding.tvTotal.text = "Rp$point"
    }

    private fun initView() {
        if (dateFrom!!.isEmpty()) {
            dateFrom = "dd-mm-yyyy"
            dateTo = "dd-mm-yyyy"
            size = 0

            val total = 350000 * size!!
            binding.tvDateTo.text = dateTo
            binding.tvDateFrom.text = dateFrom
            binding.tvPriceEstimate.text = "Rp350.000 / 24 HOUR X $size Days"
            setNominal(total)
        } else {
            val total = 350000 * size!!
            binding.tvDateTo.text = dateTo
            binding.tvDateFrom.text = dateFrom
            binding.tvPriceEstimate.text = "Rp350.000 / 24 HOUR X $size Days"
            setNominal(total)
        }
    }

    fun changeBackgroundDriver(buttonEnable: TextView, buttonDisable: TextView) {

        buttonEnable.setBackgroundColor(resources.getColor(R.color.orange))
        buttonEnable.setTextColor(resources.getColor(R.color.white))

        buttonDisable.setBackgroundColor(resources.getColor(R.color.white))
        buttonDisable.setTextColor(resources.getColor(R.color.black))


    }

    fun changeBackgroundTransmission(
        buttonEnable: LinearLayout,
        textEnable1: TextView,
        textEnable2: TextView,
        buttonDisable: LinearLayout,
        textDisable1: TextView,
        textDisable2: TextView
    ) {

        buttonEnable.setBackgroundColor(resources.getColor(R.color.orange))
        textEnable1.setTextColor(resources.getColor(R.color.white))
        textEnable2.setTextColor(resources.getColor(R.color.white))

        buttonDisable.setBackgroundColor(resources.getColor(R.color.white))
        textDisable1.setTextColor(resources.getColor(R.color.black))
        textDisable2.setTextColor(resources.getColor(R.color.black))

    }
}