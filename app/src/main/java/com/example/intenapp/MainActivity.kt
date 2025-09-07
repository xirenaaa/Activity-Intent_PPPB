package com.example.intenapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intenapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_NAME = "data_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnToSecondActivity.setOnClickListener {
                val intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java)

                intentToSecondActivity.putExtra(EXTRA_NAME, edtName.text.toString())

                startActivity(intentToSecondActivity)
            }
        }
    }
}