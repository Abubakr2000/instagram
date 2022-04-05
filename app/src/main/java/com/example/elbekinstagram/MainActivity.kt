package com.example.elbekinstagram

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.elbekinstagram.fragments.ComposeFragment
import com.example.elbekinstagram.fragments.HomeFragment
import com.example.elbekinstagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

// Let user create a post by taking a photo with their camera

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
                item ->

            var fragmenttoShow: Fragment? = null
            when (item.itemId) {

                R.id.action_home -> {
                    fragmenttoShow = HomeFragment()
                }
                R.id.action_compose -> {
                    fragmenttoShow = ComposeFragment()
                }
                R.id.action_profile -> {
                    fragmenttoShow = ProfileFragment()
                }
            }

            if(fragmenttoShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmenttoShow).commit()
            }

            // Return true to say that we have handled this user interaction on th item
            true
        }
        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home

        }

    //queryPosts()



    companion object {
        const val TAG = "MainActivity"
    }
}