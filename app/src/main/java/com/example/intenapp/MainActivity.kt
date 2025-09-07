package com.example.intenapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intenapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_NAME = "data_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToSecondActivity.setOnClickListener {
            val intentToSecondActivity = Intent(this, SecondActivity::class.java)
            intentToSecondActivity.putExtra(EXTRA_NAME, binding.edtName.text.toString())
            startActivity(intentToSecondActivity)
        }
    }
}