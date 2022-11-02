package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var textInput : TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInput = findViewById(R.id.input)
    }

    fun onDigit(view: View){
        textInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onClear(view: View){
        textInput?.text = ""
    }
    fun onDecimal(view: View){
        if (!lastDot && lastNumeric){
            textInput?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }
    fun onOperator(view: View){
        textInput?.text?.let{
            if (lastNumeric && !isOperator(it.toString())){
                textInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    fun onEquals(view: View){
        if(lastNumeric){
            var textValue = textInput?.text.toString()
            var prefix = ""
            try{
                if (textValue.startsWith("-")){
                    prefix = "-"
                    textValue = textValue.substring(1)
                }
                if (textValue.contains("-")){
                    var splitText = textValue.split("-")
                    var one = splitText[0]
                    var two = splitText[1]
                    if(prefix.isNotEmpty()){
                    one = prefix + one
                    }
                    textInput?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }
                else if (textValue.contains("+")){
                    var splitText = textValue.split("+")
                    var one = splitText[0]
                    var two = splitText[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    textInput?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }
                else if (textValue.contains("/")){
                    var splitText = textValue.split("/")
                    var one = splitText[0]
                    var two = splitText[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    textInput?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
                else if (textValue.contains("*")){
                    var splitText = textValue.split("*")
                    var one = splitText[0]
                    var two = splitText[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    textInput?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){e.printStackTrace()}
        }
    }
}

    private fun isOperator(value:String):Boolean {
        return if(value.startsWith("-")){
            false
        }else{ value.contains("/")|| value.contains("+")
                || value.contains("-") || value.contains("*")
    }
}
    private fun removeZero(result:String): String {
        var value = result
        if(result.contains(".")){
            value= result.substring(0, result.length - 2)
        }
        return value
     }