package com.rentwiz.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.rentwiz.app.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/*class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sizeState by remember {
                mutableStateOf(200.dp)
            }
            val size by animateDpAsState(targetValue = sizeState)
           Box(modifier = Modifier
               .size(size)
               .background(Color.Cyan),
               contentAlignment = Alignment.Center
           ) {
                Column {
                    Button(onClick = {
                        sizeState += 10.dp;
                    }) {
                        Text("Increase size")
                    }
                    Button(onClick = {
                        sizeState -= 10.dp;
                    }) {
                        Text("Decrease size")
                    }
                }
           }
        }
    }
}*/

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

 private lateinit var navController: NavController
 private lateinit var appBarConfiguration: AppBarConfiguration

 private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(binding.root)
     init()
     setupBottomNavMenu()
 }

 private fun init() {
     setSupportActionBar(binding.appBarLayout.toolbar)
     supportActionBar?.title = "Rentwiz"
 }

 override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     menuInflater.inflate(com.rentwiz.app.coreui.R.menu.help_menu, menu)
     return true
 }

 override fun onOptionsItemSelected(item: MenuItem): Boolean {
     return when(item.itemId) {
         com.rentwiz.app.coreui.R.id.help -> {
             true
         }
         else -> {
             false
         }
     }
 }

 private fun setupBottomNavMenu() {
     navController = findNavController(R.id.home_nav_host_fragment)
     binding.bottomNavView.setupWithNavController(navController)
 }

 override fun onSupportNavigateUp(): Boolean {
     return navController.navigateUp(appBarConfiguration)
 }
}