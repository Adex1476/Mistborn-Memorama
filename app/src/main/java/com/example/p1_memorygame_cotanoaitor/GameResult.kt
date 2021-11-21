package com.example.p1_memorygame_cotanoaitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameResult : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var playButton: Button
    private lateinit var menuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_result)

        resultTextView = findViewById(R.id.result_textview)
        playButton = findViewById(R.id.playAgain_button)
        menuButton = findViewById(R.id.quit_button)

        val bundle: Bundle? = intent.extras
        val layout: Int? =bundle?.getInt("Joc")
        resultTextView.setText(bundle?.getString(RESULT_KEY) + " /100")

        playButton.setOnClickListener {
            when (layout) {
                1 -> intent = Intent(this, GamePlay::class.java)
                2 -> intent = Intent(this, GamePlayH::class.java)
                3 -> intent = Intent(this, GamePlayA::class.java)
                else -> {
                    intent = Intent(this, GamePlay::class.java)
                }
            }
            startActivity(intent)
        }

        menuButton.setOnClickListener {
            startActivity(Intent(this, MainGame::class.java))
        }
    }
}