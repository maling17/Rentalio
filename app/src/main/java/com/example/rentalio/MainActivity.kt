package com.example.rentalio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rentalio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.newsNavHostFragment))
        findNavController(R.id.newsNavHostFragment).addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.homeFragment ||destination.id == R.id.historyFragment2||destination.id == R.id.helpFragment2||destination.id == R.id.accountFragment) {
                binding.bottomNavigationView.visibility = View.VISIBLE
                if (destination.id == R.id.historyFragment2||destination.id == R.id.helpFragment2||destination.id == R.id.accountFragment){
                    Toast.makeText(this, "Upcoming Update", Toast.LENGTH_SHORT).show()
                }

            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }

        }
    }
}