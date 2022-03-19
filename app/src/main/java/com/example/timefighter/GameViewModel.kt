package com.example.timefighter

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    private var _timeLeft = MutableLiveData(20)
    val timeLeft: LiveData<Int> = _timeLeft

    private var _toast = MutableLiveData<Boolean>(false)
    val toast: LiveData<Boolean> = _toast

    //чтобы два раза не запустить одну игру
    private var gameStarted = false

    lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 20000
    private var countDownInterval: Long = 1000

    init {
        resetGame()
    }

    fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }
        _score.value = _score.value?.inc()

    }

    private fun resetGame() {

        _score.value = 0

        countDownTimer = object : CountDownTimer(
            initialCountDown, countDownInterval
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished.toInt() / 1000
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false

    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    fun endGame() {
            _toast.value = true
            resetGame()
    }
}