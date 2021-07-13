package com.example.rentalio.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rentalio.Preferences
import com.example.rentalio.R
import com.example.rentalio.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    var _binding:HomeFragmentBinding? = null
    val binding get() = _binding!!
    lateinit var preferences: Preferences
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        preferences = Preferences(requireContext())
        imageSlide()

        binding.ivCity.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }
        binding.ivSuv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }
        binding.ivMvp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }
        binding.ivJeep.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }

        binding.ivMoped.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }
        binding.ivClassic.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }
        binding.ivTrail.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }
        binding.ivScooter.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment)
        }

    }
    private fun imageSlide() {
        // untuk banner promo
        val promo = ArrayList<SlideModel>()
        promo.add(SlideModel(R.drawable.banner_slider1))
        promo.add(SlideModel(R.drawable.banner_slider2))
        binding.sliderPromo.setImageList(promo, ScaleTypes.FIT)
        binding.sliderPromo.startSliding(3000L)
    }

}