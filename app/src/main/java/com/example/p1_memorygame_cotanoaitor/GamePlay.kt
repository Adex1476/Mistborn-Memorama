package com.example.p1_memorygame_cotanoaitor

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val RESULT_KEY = "RESULT"

class GamePlay : AppCompatActivity(), View.OnClickListener {

    private  lateinit var cartas: List<ImageView>
    private lateinit var carta1: ImageView
    private lateinit var carta2: ImageView
    private lateinit var carta3: ImageView
    private lateinit var carta4: ImageView
    private lateinit var carta5: ImageView
    private lateinit var carta6: ImageView
    private lateinit var resetButton: Button
    private lateinit var pauseButton: Button

    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_play)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        carta1 = findViewById(R.id.revers1)
        carta2 = findViewById(R.id.revers2)
        carta3 = findViewById(R.id.revers3)
        carta4 = findViewById(R.id.revers4)
        carta5 = findViewById(R.id.revers5)
        carta6 = findViewById(R.id.revers6)
        resetButton = findViewById(R.id.reset_button)
        pauseButton = findViewById(R.id.pauseButton)

        carta1.setOnClickListener(this)
        carta2.setOnClickListener(this)
        carta3.setOnClickListener(this)
        carta4.setOnClickListener(this)
        carta5.setOnClickListener(this)
        carta6.setOnClickListener(this)

        resetButton.setOnClickListener {
            viewModel.resetEstatJoc()
            viewModel.lastCard = -1
            viewModel.resultat = 0
            viewModel.movements = 0
            updateUI()
        }

        pauseButton.setOnClickListener {
            val pauseDialog = AlertDialog.Builder(this)
            pauseDialog.setMessage("PAUSE")
                .setMessage("Score: " + viewModel.calcularResultat())
                .setNegativeButton("Return to game",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            val missage: Dialog = pauseDialog.create()
            missage.setCanceledOnTouchOutside(false)
            missage.show()
        }
        updateUI()
    }

    override fun onClick(v: View?) {
        when (v) {
            carta1 -> if(!viewModel.getBlocked(0) && !viewModel.animation) girarCarta(0, carta1)
            carta2 -> if(!viewModel.getBlocked(1) && !viewModel.animation) girarCarta(1, carta2)
            carta3 -> if(!viewModel.getBlocked(2) && !viewModel.animation) girarCarta(2, carta3)
            carta4 -> if(!viewModel.getBlocked(3) && !viewModel.animation) girarCarta(3, carta4)
            carta5 -> if(!viewModel.getBlocked(4) && !viewModel.animation) girarCarta(4, carta5)
            carta6 -> if(!viewModel.getBlocked(5) && !viewModel.animation) girarCarta(5, carta6)
        }
        viewModel.movements++
        if (viewModel.finalJoc()) {
            val intent = Intent(this, GameResult::class.java)
            intent.putExtra(RESULT_KEY, viewModel.calcularResultat().toString())
            intent.putExtra("Joc", 1)
            startActivity(intent)
        }
    }

    private fun girarCarta(idCarta: Int, carta: ImageView) {
        carta.setImageResource(viewModel.girarCarta(idCarta))
        println(viewModel.lastCard)
        if (viewModel.lastCard < 0) {
            viewModel.lastCard = idCarta
        } else {
            viewModel.animation = true
            val amagarCartes = object: CountDownTimer(500, 500) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    if (!viewModel.compararCarta(idCarta)) {
                        carta.setImageResource(viewModel.regirarCarta(idCarta))
                        carta.setImageResource(viewModel.regirarCarta(viewModel.lastCard))
                    }
                    viewModel.lastCard = -1
                    viewModel.animation = false
                    updateUI()
                }
            }
            amagarCartes.start()
        }
        println(viewModel.lastCard)
    }

    private fun updateUI() {
        carta1.setImageResource(viewModel.estatCarta(0))
        carta2.setImageResource(viewModel.estatCarta(1))
        carta3.setImageResource(viewModel.estatCarta(2))
        carta4.setImageResource(viewModel.estatCarta(3))
        carta5.setImageResource(viewModel.estatCarta(4))
        carta6.setImageResource(viewModel.estatCarta(5))
    }
}