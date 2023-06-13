package com.huseyincn.kreditest.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.huseyincn.kreditest.R
import com.huseyincn.kreditest.viewmodel.CreditViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CreditViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CreditViewModel::class.java]
    }

    override fun onBackPressed() {
        viewModel.clearData()
        supportFragmentManager.popBackStack()
//        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//
//        builder.setMessage("You wanna leave the aplication?")
//            .setPositiveButton("Yes", { dialog, which -> this.finishAffinity() })
//            .setNegativeButton("No", { dialog, which ->  }).show()
    }
}