package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initView()
    }

    private fun initView() {
        mainBinding.zero.setOnClickListener { input("0", false) }
        mainBinding.one.setOnClickListener { input("1", false) }
        mainBinding.two.setOnClickListener { input("2", false) }
        mainBinding.three.setOnClickListener { input("3", false) }
        mainBinding.four.setOnClickListener { input("4", false) }
        mainBinding.five.setOnClickListener { input("5", false) }
        mainBinding.six.setOnClickListener { input("6", false) }
        mainBinding.seven.setOnClickListener { input("7", false) }
        mainBinding.eight.setOnClickListener { input("8", false) }
        mainBinding.nine.setOnClickListener { input("9", false) }
        mainBinding.backspace.setOnClickListener {
            val expression = mainBinding.calculation.text.toString()
            if (expression.isNotEmpty()) {
                mainBinding.calculation.text = expression.substring(0, expression.length - 1)
            }


        }
        mainBinding.clearText.setOnClickListener { input("", true) }

        mainBinding.divide.setOnClickListener { input(" / ", false) }
        mainBinding.muti.setOnClickListener { input(" * ", false) }
        mainBinding.minus.setOnClickListener { input(" - ", false) }
        mainBinding.plus.setOnClickListener { input(" + ", false) }
        mainBinding.button42.setOnClickListener {
            try {
                val expression = ExpressionBuilder(mainBinding.calculation.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                mainBinding.textView.visibility = View.VISIBLE
                if (result == longResult.toDouble()) {

                    mainBinding.result.text = longResult.toString()
                } else
                    mainBinding.result.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }

        }

        mainBinding.calculation.doAfterTextChanged {
            mainBinding.textView.visibility = View.GONE
        }
    }


    private fun input(string: String, isClear: Boolean) {
        if (isClear) {
            mainBinding.calculation.text = ""
            mainBinding.result.text = ""
            mainBinding.textView.visibility=View.GONE
        } else {
            mainBinding.calculation.append(string)
        }
    }


}