package com.example.p1_memorygame_cotanoaitor

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var lastCard: Int = -1
    var imatges = arrayOf(
        R.drawable.carta1,
        R.drawable.carta2,
        R.drawable.carta3,
        R.drawable.carta1,
        R.drawable.carta2,
        R.drawable.carta3
    )

    private var cartes = mutableListOf<Carta>()
    public var movements = 0
    public var resultat = 0
    public var animation = false

    init {
        setDataModel()
    }

    private fun setDataModel() {
        imatges.shuffle()
        for (i in 0..(imatges.size - 1)) {
            cartes.add(Carta(i, imatges[i]))
        }
    }

    fun girarCarta(idCarta: Int) : Int {
        cartes[idCarta].girada = true
        return cartes[idCarta].resId
    }

    fun regirarCarta(idCarta: Int) : Int {
        cartes[idCarta].girada = false
        return R.drawable.revers
    }

    fun compararCarta(idCarta: Int) : Boolean {
        if (cartes[idCarta].resId == cartes[lastCard].resId) {
            cartes[idCarta].blocked = true
            cartes[lastCard].blocked = true
            return true
        } else {
            return false
        }
    }

    fun getBlocked(idCarta: Int): Boolean {
        return cartes[idCarta].blocked
    }

    fun finalJoc(): Boolean {
        for (i in 0..(imatges.size - 1)) {
            if (!cartes[i].girada) return false
        }
        return true
    }

    fun resetEstatJoc() {
        for (i in 0..(imatges.size - 1)) {
            cartes[i].girada = false
        }
    }

    fun estatCarta(idCarta: Int): Int {
        if(cartes[idCarta].girada) return cartes[idCarta].resId
        else return R.drawable.revers
    }

    fun calcularResultat(): Int {
        resultat = 100 - (movements - 6) * 10
        if (resultat >= 0) {
            return  resultat
        } else {
            return 0
        }
    }
}