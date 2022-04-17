package com.example.word_io

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.word_io.data.DataSource
import com.example.word_io.model.Question

class MainActivity : AppCompatActivity() {
    private var currentStep = 0;
    private var score = 0;
    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questions = DataSource().loadQuestions()

        showQuestion()

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener { onSubmit() }

        val skipButton = findViewById<Button>(R.id.skip_button)
        skipButton.setOnClickListener { onSkip() }

        val submittedText = findViewById<EditText>(R.id.word_input_edit_text)
        submittedText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun showQuestion() {
        val question = questions[currentStep]

        val wordText = findViewById<TextView>(R.id.word_text)
        wordText.text = scrambleWord(question.word)

        val descriptionText = findViewById<TextView>(R.id.description_text)
        descriptionText.text = question.description

        val stepText = findViewById<TextView>(R.id.step_text)
        stepText.text = "Step ${currentStep + 1} of ${questions.size}"

        val scoreText = findViewById<TextView>(R.id.score_text)
        scoreText.text = "Score: $score"
    }

    private fun onSubmit() {
        if (currentStep >= questions.size - 1) {
            redirectToGameOverScreen()
            return
        }

        val submittedText = findViewById<EditText>(R.id.word_input_edit_text)
        if (submittedText.text.toString().lowercase() != questions[currentStep].word.lowercase()) {
            submittedText.error = "Wrong Guess. Try again!"
            submittedText.setText("")
            return
        }

        submittedText.setText("")
        score += 10
        next()
    }

    private fun onSkip() {
        if (currentStep >= questions.size - 1) {
            redirectToGameOverScreen()
            return
        }
        next()
    }

    private fun scrambleWord(word: String): String {
        var blankLetter = (1 until word.length-2).random()
        var newWord = ""
        val letterIndex = mutableListOf<Int>()

        while(blankLetter != 0) {
            var randomLetter = (word.indices).random()
            if(!letterIndex.contains(randomLetter)) {
                letterIndex.add(randomLetter)
                blankLetter--;
            }
        }

        for(i in word.indices) {
            if(letterIndex.contains(i)) {
                newWord += "_"
            } else {
                newWord += word[i]
            }
        }

        return newWord;
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun next() {
        ++currentStep
        showQuestion()
    }

    private fun redirectToGameOverScreen() {
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra(GameOverActivity.SCORE, score)
        startActivity(intent)
    }
}