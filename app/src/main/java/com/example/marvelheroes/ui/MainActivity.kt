package com.example.marvelheroes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Holds a single FragmentContainerView
 *
 * Navigation logic is delegated to Navigation Component
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}