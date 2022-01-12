package com.example.test

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.text.TextRunShaper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.layout_popup.*

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private var mcurrentPosition:Int=1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOption:Int = 0
    private var mCorrectAnswers:Int=0
    private var mCheck1:Boolean=false
    private var mCheck2:Boolean=false
    private var mCheck3:Boolean=false
    private var mCheck4:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mQuestionList = Constants.getQuestions()
        setQuestion()
        op1.setOnClickListener(this)
        op2.setOnClickListener(this)
        op3.setOnClickListener(this)
        op4.setOnClickListener(this)
        btnsubmit.setOnClickListener(this)

    }

    override fun onBackPressed() {
        val builder:AlertDialog.Builder =  AlertDialog.Builder(this)
        builder.setTitle("EXIT..!! ")
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24)
        builder.setMessage("Are you sure you want to RETURN to home screen?\nAll your data will be lost!!")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { dialoginterface: DialogInterface, i: Int ->
            finish()

        }
        builder.setNegativeButton("NO"){dialoginterface:DialogInterface,i:Int->

        }
        val builder1=builder.create()
        builder1.show()
    }
    private fun setQuestion(){
        mCheck1=false
        mCheck2=false
        mCheck3=false
        mCheck4=false
        op1.isEnabled=true
        op2.isEnabled=true
        op3.isEnabled=true
        op4.isEnabled=true
        defaultoptionsview()
        if(mcurrentPosition==mQuestionList!!.size){
            btnsubmit.text="FINISH"
        }else{
            btnsubmit.text="SUBMIT"
        }
        val question = mQuestionList!![mcurrentPosition-1]
        tv1.text=question.questions
        op1.text=question.option1
        op2.text=question.option2
        op3.text=question.option3
        op4.text=question.option4
    }
    private fun defaultoptionsview(){
        val options=ArrayList<TextView>()
        options.add(0,op1)
        options.add(1,op2)
        options.add(2,op3)
        options.add(3,op4)
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.op1->{
                mCheck4=false
                mCheck3=false
                mCheck2=false
                if(mCheck1==false){
                    selectedoptionview(op1,1)
                    mCheck1=true
                }else{
                    defaultoptionsview()
                    mSelectedOption=0
                    mCheck1=false
                }
            }
            R.id.op2->{
                mCheck4=false
                mCheck3=false
                mCheck1=false
                if(mCheck2==false){
                    selectedoptionview(op2,2)
                    mCheck2=true
                }else{
                    defaultoptionsview()
                    mSelectedOption=0
                    mCheck2=false
                }
            }
            R.id.op3->{
                mCheck4=false
                mCheck1=false
                mCheck2=false
                if(mCheck3==false){
                    selectedoptionview(op3,3)
                    mCheck3=true
                }else{
                    defaultoptionsview()
                    mSelectedOption=0
                    mCheck3=false
                }
            }
            R.id.op4->{
                mCheck1=false
                mCheck3=false
                mCheck2=false
                if(mCheck4==false){
                    selectedoptionview(op4,4)
                    mCheck4=true
                }else{
                    defaultoptionsview()
                    mSelectedOption=0
                    mCheck4=false
                }
            }
            R.id.btnsubmit->{
                if(mSelectedOption==0){
                    mcurrentPosition++
                    when {
                        mcurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }else-> {
                        val window = PopupWindow(this)
                        val view = layoutInflater.inflate(R.layout.layout_popup,null)
                        window.contentView=view
                        val btnok:Button=view.findViewById(R.id.btnok)
                        val tv:TextView=view.findViewById(R.id.tv_score)
                        tv.text="SCORE: $mCorrectAnswers/5"
                        btnok.setOnClickListener(){
                            finish()
                            window.dismiss()
                        }
                        window.setBackgroundDrawable(getDrawable(R.drawable.popup_background))
                        window.showAtLocation(btnsubmit,Gravity.CENTER,0,0)
                        window.isFocusable
                        blur_image.visibility=View.VISIBLE
                        btnsubmit.visibility=View.INVISIBLE
                        btnhint.visibility=View.INVISIBLE
                        }
                    }
                }else{
                    val question=mQuestionList!!.get(mcurrentPosition-1)
                    if (question.correctAnswer!=mSelectedOption){
                        answerview(mSelectedOption,R.drawable.wrong_option)
                    }else{
                        mCorrectAnswers++
                    }
                    answerview(question.correctAnswer,R.drawable.correct_option)
                    op1.isEnabled=false
                    op2.isEnabled=false
                    op3.isEnabled=false
                    op4.isEnabled=false
                    if(mcurrentPosition==mQuestionList!!.size){
                        btnsubmit.text="FINISH"
                    }else{
                        btnsubmit.text="GO TO NEXT QUESTION"
                    }
                    mSelectedOption=0
                }
            }
        }
    }
    private fun answerview(answer:Int,drawableview:Int){
        when(answer){
            1->{
                op1.background=ContextCompat.getDrawable(this,drawableview)
            }
            2->{
                op2.background=ContextCompat.getDrawable(this,drawableview)
            }
            3->{
                op3.background=ContextCompat.getDrawable(this,drawableview)
            }
            4->{
                op4.background=ContextCompat.getDrawable(this,drawableview)
            }
        }
    }
    private fun selectedoptionview(v:TextView,num:Int){
        defaultoptionsview()
        mSelectedOption = num
        v.setTextColor(Color.parseColor("#363A43"))
        v.setTypeface(v.typeface,Typeface.BOLD)
        v.background=ContextCompat.getDrawable(this,R.drawable.selected_option)
    }
}