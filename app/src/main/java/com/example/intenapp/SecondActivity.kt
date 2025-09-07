package com.example.intenapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.intenapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val address = data?.getStringExtra(InputAddressActivity.EXTRA_ADDRESS)
            binding.txtAddress.text = "Alamat: $address"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        binding.txtName.text = "Halo, ${name}!"

        binding.btnToThirdActivity.setOnClickListener {
            val intentToInputAddress = Intent(this@SecondActivity, InputAddressActivity::class.java)
            launcher.launch(intentToInputAddress)
        }
    }
}