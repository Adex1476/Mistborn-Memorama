package com.example.p1_memorygame_cotanoaitor

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainGame : AppCompatActivity() {
    var act_index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_P1_memoryGame_cotanoaitor)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_main)
        var playButton: Button= findViewById(R.id.playButton)
        var helpButton: Button= findViewById(R.id.helpButton)
        var intent :Intent
        val spinner: Spinner = findViewById(R.id.spinner)
        val listDifficulties = resources.getStringArray(R.array.Difficulty)
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item , listDifficulties)
        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(listDifficulties[p2]){
                    "EASY"-> act_index = 1
                    "HARD"-> act_index = 2
                    "MISTBORN"-> act_index = 3
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                act_index = 1
            }
        }

        playButton.setOnClickListener {
            when (act_index) {
                1 -> intent = Intent(this, GamePlay::class.java)
                2 -> intent = Intent(this, GamePlayH::class.java)
                3 -> intent = Intent(this, GamePlayA::class.java)
                else -> {
                    intent = Intent(this, GamePlay::class.java)
                }
            }
            println(intent.toString())
            startActivity(intent)
        }


        helpButton.setOnClickListener {
            val helpDialog = AlertDialog.Builder(this)
            helpDialog.setMessage("HELP")
                .setMessage("In this game you have to make pairs with the cards that have the same image. On MISTBORN mode you have to make alomantic pairs.")
                .setNegativeButton("Return to menu",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Torna al menu!
                    })
            val missage: Dialog = helpDialog.create()
            missage.setCanceledOnTouchOutside(false)
            missage.show()
        }
    }
}



