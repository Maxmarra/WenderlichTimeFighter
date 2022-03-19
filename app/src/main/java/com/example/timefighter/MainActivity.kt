package com.example.timefighter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.timefighter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.gameViewModelLayoutVar = gameViewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)

        gameViewModel.toast.observe(this
        ) { Toast.makeText(this, getString(R.string.game_over_message, gameViewModel.score.value), Toast.LENGTH_LONG).show()
        }
    }

}