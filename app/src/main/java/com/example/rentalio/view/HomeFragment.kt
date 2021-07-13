package com.example.rentalio.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rentalio.Preferences
import com.example.rentalio.R
import com.example.rentalio.adapter.KategoriAdapter
import com.example.rentalio.databinding.HomeFragmentBinding
import com.example.rentalio.model.KategoriModel


class HomeFragment : Fragment() {

    var _binding: HomeFragmentBinding? = null
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
        _binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        preferences = Preferences(requireContext())
        imageSlide()
        getKategori()

    }

    private fun imageSlide() {
        // untuk banner promo
        val promo = ArrayList<SlideModel>()
        promo.add(SlideModel(R.drawable.banner_slider1))
        promo.add(SlideModel(R.drawable.banner_slider2))
        binding.sliderPromo.setImageList(promo, ScaleTypes.FIT)
        binding.sliderPromo.startSliding(3000L)
    }

    private fun getKategori() {
        val kategoriList: List<KategoriModel> = listOf(
            KategoriModel("CITY", R.drawable.city),
            KategoriModel("SUV", R.drawable.suv),
            KategoriModel("MVP", R.drawable.mpv),
            KategoriModel("JEEP", R.drawable.jeep),
            KategoriModel("CLASSIC", R.drawable.classic),
            KategoriModel("MOPED", R.drawable.moped),
            KategoriModel("SCOOTER", R.drawable.scooter),
            KategoriModel("TRAIL", R.drawable.trail),
        )

        val adapter = KategoriAdapter(this@HomeFragment)
        adapter.setOnItemClickListener { findNavController().navigate(R.id.action_homeFragment2_to_bookingFragment) }
        adapter.setProductList(kategoriList)
        binding.rvKategori.adapter = adapter
        binding.rvKategori.layoutManager =
            GridLayoutManager(requireContext(), 4)
        binding.rvKategori.addItemDecoration(SpacesItemDecoration(40))
    }

    class SpacesItemDecoration(private val space: Int) : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = space
            outRect.right = space
            outRect.bottom = space

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space
            } else {
                outRect.top = 0
            }
        }
    }

}