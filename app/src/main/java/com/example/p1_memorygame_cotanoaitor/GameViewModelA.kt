package com.example.p1_memorygame_cotanoaitor

import androidx.lifecycle.ViewModel

class GameViewModelA : ViewModel() {

    var lastCard: Int = -1
    var imatges = arrayOf(
        imageLink(R.drawable.carta4, 1),
        imageLink(R.drawable.carta5, 1),
        imageLink(R.drawable.carta6, 2),
        imageLink(R.drawable.carta7, 2),
        imageLink(R.drawable.carta8, 3),
        imageLink(R.drawable.carta9, 3),
        imageLink(R.drawable.carta10, 4),
        imageLink(R.drawable.carta11, 4)
    )

    private var cartes = mutableListOf<CartaA>()
    public var movements = 0
    public var resultat = 0
    public var animation = false

    init {
        setDataModel()
    }

    private fun setDataModel() {
        imatges.shuffle()
        for (i in 0..(imatges.size - 1)) {
            cartes.add(CartaA(i, imatges[i]))
        }
    }

    fun girarCarta(idCartaA: Int) : Int {
        cartes[idCartaA].girada = true
        return cartes[idCartaA].resId.element
    }

    fun regirarCarta(idCartaA: Int) : Int {
        cartes[idCartaA].girada = false
        return R.drawable.revers
    }

    fun compararCarta(idCartaA: Int) : Boolean {
        if (cartes[idCartaA].resId.link == cartes[lastCard].resId.link) {
            cartes[idCartaA].blocked = true
            cartes[lastCard].blocked = true
            return true
        } else {
            return false
        }
    }

    fun getBlocked(idCartaA: Int): Boolean {
        return cartes[idCartaA].blocked
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

    fun estatCarta(idCartaA: Int): Int {
        if(cartes[idCartaA].girada) return cartes[idCartaA].resId.element
        else return R.drawable.revers
    }

    fun calcularResultat(): Int {
        resultat = 100 - (movements - 10) * 10
        if (resultat >= 0) {
            return  resultat
        } else {
            return 0
        }
    }
}