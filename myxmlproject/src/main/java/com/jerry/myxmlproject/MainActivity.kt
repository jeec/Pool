package com.jerry.myxmlproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            title = "My Toolbar Title"
            it.setDisplayHomeAsUpEnabled(true)
            it.subtitle = "sub title"
        }

        // 设置标题
//        supportActionBar!!.title = ""
//
//        toolbar.setNavigationContentDescription(R.string.accessible_back_button_1)
//
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        // 设置导航图标
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//        supportActionBar!!.setDisplayShowTitleEnabled(false)


//        supportActionBar!!.setHomeAsUpIndicator(com.google.android.material.R.drawable.ic_arrow_back_black_24)

//        supportActionBar!!.setHomeActionContentDescription(R.string.accessible_back_button)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}