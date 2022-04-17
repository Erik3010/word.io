package com.example.word_io

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity: AppCompatActivity() {
    companion object {
        const val SCORE = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over_activity)

        val score = intent?.extras?.getInt(SCORE).toString()

        val gameOverScore = findViewById<TextView>(R.id.game_over_score)
        gameOverScore.text = "Your final score: $score"

        var restartButton = findViewById<Button>(R.id.restart_button)
        restartButton.setOnClickListener { onRestartGame() }
    }

    private fun onRestartGame() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}