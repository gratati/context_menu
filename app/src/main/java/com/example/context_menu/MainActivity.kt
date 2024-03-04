package com.example.context_menu

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var randomButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        randomButton = findViewById(R.id.randomButton)

        registerForContextMenu(editText)

        randomButton.setOnClickListener {
            val randomNumber = Random.nextInt(1, 51)
            editText.setText(randomNumber.toString())
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.colorQuality -> {
                val grade = editText.text.toString().toIntOrNull() ?: return true
                setColorByGrade(grade)
                true
            }
            R.id.exitApp -> {
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setColorByGrade(grade: Int) {
        val color = when (grade) {
            in 1..10 -> android.graphics.Color.parseColor("#FF0000") // Красный
            in 11..20 -> android.graphics.Color.parseColor("#FFA500") // Оранжевый
            in 21..30 -> android.graphics.Color.parseColor("#FFFF00") // Желтый
            in 31..40 -> android.graphics.Color.parseColor("#00FF00") // Зеленый
            in 41..50 -> android.graphics.Color.parseColor("#0000FF") // Синий
            else -> return
        }
        editText.setBackgroundColor(color)
    }
}
