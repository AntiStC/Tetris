package ru.antistc.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.GameActivity
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar
import ru.antistc.myapplication.storage.AppPreferences
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val btnNewGame = findViewById<Button>(R.id.btn_new_game)
        val btnResetScope = findViewById<Button>(R.id.btn_reset_scope)
        val btnExit = findViewById<Button>(R.id.btn_exit)
        tvHighScore = findViewById(R.id.tv_high_score)

        btnNewGame.setOnClickListener(this::onBtnNewGameClick)
        btnResetScope.setOnClickListener(this::onBtnResetScopeClick)
        btnExit.setOnClickListener(this::onBtnExitClick)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun onBtnNewGameClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onBtnResetScopeClick(view: View) {
        val preferences = AppPreferences(this)
        preferences.clearHighScore()
        Snackbar.make(view, "Scope successfully reset", Snackbar.LENGTH_SHORT).show()
        tvHighScore?.text = "High score: ${preferences.getHighScore()}"
    }

    private fun onBtnExitClick(view: View): Nothing = exitProcess(0)
}