package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculater.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.exp

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumbersClicked()
        onOperatorClicked()

    }

    private fun onNumbersClicked() {
        binding.btn0.setOnClickListener {
            if(binding.txtAmaliat.text.isNotEmpty()){
                appendText("0")
            }

        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
            if(binding.txtAmaliat.text.isEmpty() || binding.txtJavab.text.isNotEmpty()){
                appendText("0.")
            }else if(!binding.txtAmaliat.text.contains(".")){
                appendText(".")
            }

        }
    }

    private fun onOperatorClicked() {
        binding.btnAC.setOnClickListener {
            binding.txtAmaliat.text=""
            binding.txtJavab.text=""
        }
        binding.btnParBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnParBaste.setOnClickListener {
            appendText(")")
        }
        binding.btnJam.setOnClickListener {

            if(binding.txtAmaliat.text.isNotEmpty()){
                val myChar = binding.txtAmaliat.text.last()
                if(myChar!='+' && myChar!='-' && myChar !='*' && myChar !='/'){
                    appendText("+")
                }
            }



        }
        binding.btnMenha.setOnClickListener {
            if(binding.txtAmaliat.text.isNotEmpty()){
                val myChar = binding.txtAmaliat.text.last()
                if(myChar!='+' && myChar!='-' && myChar !='*' && myChar !='/'){
                    appendText("-")
                }
            }

        }
        binding.btnZarb.setOnClickListener {
            if(binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("*")
                }
            }
        }
        binding.btnTaghsim.setOnClickListener {
            if(binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("/")
                }
            }
        }
        binding.btnPakidan.setOnClickListener {
            val oldText = binding.txtAmaliat.text.toString()
            if(oldText.isNotEmpty()){
                binding.txtAmaliat.text =oldText.substring(0,oldText.length-1)
            }
        }
        binding.btnMosavi.setOnClickListener {
            try {

                val expression = ExpressionBuilder(binding.txtAmaliat.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }
            }catch (e:Exception){
                binding.txtJavab.text=""
                binding.txtAmaliat.text=""
                Toast.makeText(this, "Invalid format used!", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun appendText(newText:String){
        if(binding.txtJavab.text.isNotEmpty()){
            binding.txtAmaliat.text = ""
            binding.txtJavab.text=""
        }
        binding.txtAmaliat.append(newText)
        val viewTree:ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizontalScrollViewTxtExpression.smoothScrollTo(binding.txtAmaliat.width,0)
            }
        })
    }
}