package com.example.buzzebeesassignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.buzzebeesassignment.R
import com.example.buzzebeesassignment.fragment.DescriptionFragment
import com.example.buzzebeesassignment.fragment.MainFragment
import com.example.buzzebeesassignment.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.selectedCampaign.observe(this, Observer {
            supportFragmentManager.beginTransaction().add(R.id.flContainer, DescriptionFragment()).commit()
        })

        supportFragmentManager.beginTransaction().add(R.id.flContainer, MainFragment()).commit()
    }
}