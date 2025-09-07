package com.example.intenapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intenapp.databinding.ActivityInputAddressBinding

class InputAddressActivity : AppCompatActivity() {

    lateinit var binding: ActivityInputAddressBinding

    companion object {
        const val EXTRA_ADDRESS = "data_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSubmit.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_ADDRESS, edtAddress.text.toString())
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}