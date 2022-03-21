package com.md.nurkhan.projectboots

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.md.nurkhan.projectboots.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.my_toolbar.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val bottmNavigationView = findViewById<BottomNavigationView>(R.id.navBottomMenu)
        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.listFragment, R.id.favoritFragment2))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottmNavigationView.setupWithNavController(navController)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFFFF")))
        supportActionBar?.elevation = 0f
        supportActionBar?.hide();



        toolbar_back.setOnClickListener {
            onBackPressed()
        }
    }

//        mMenu.setOnClickListener {
//            val view: View = layoutInflater.inflate(R.layout.button_sheet_layout, null)
//            val dialog = BottomSheetDialog(this)
//            dialog.setContentView(view)
//            dialog.show()
//        }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragmentContainerView)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}