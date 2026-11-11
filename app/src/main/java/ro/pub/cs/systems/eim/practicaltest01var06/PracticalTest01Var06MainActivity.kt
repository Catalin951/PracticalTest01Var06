package ro.pub.cs.systems.eim.practicaltest01var06

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var06MainActivity : AppCompatActivity() {
    var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var06_main)

        val buttonRandom = findViewById<Button>(R.id.buttonRandom)

        val checkLeft = findViewById<CheckBox>(R.id.leftCheckbox)
        val checkMiddle = findViewById<CheckBox>(R.id.middleCheckbox)
        val checkRight = findViewById<CheckBox>(R.id.rightCheckbox)

        val leftNumber = findViewById<EditText>(R.id.leftNumber)
        val middleNumber = findViewById<EditText>(R.id.middleNumber)
        val rightNumber = findViewById<EditText>(R.id.rightNumber)
        var random1 = ""
        var random2 = ""
        var random3 = ""

        buttonRandom.setOnClickListener {
            random1 = (1..4).random().toString()
            random2 = (1..4).random().toString()
            random3 = (1..4).random().toString()
            if (random1 == "4")
                random1 = "*"
            if (random2 == "4")
                random2 = "*"
            if (random3 == "4")
                random3 = "*"
            if (!checkLeft.isChecked)
                leftNumber.setText(random1)
            if (!checkMiddle.isChecked)
                middleNumber.setText(random2)
            if (!checkRight.isChecked)
                rightNumber.setText(random3)
            Toast.makeText(this, random1 + " " + random2 + " " + random3, Toast.LENGTH_SHORT).show()
        }

        val buttonPlay = findViewById<Button>(R.id.buttonPlay)

        buttonPlay.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var06SecondaryActivity::class.java)
            intent.putExtra("FIRST_EXTRA", random1) // put something for them
            intent.putExtra("SECOND_EXTRA", random2) // put something for them
            intent.putExtra("THIRD_EXTRA", random3) // put something for them
            var numberOfCheckboxes = 0
            if (checkLeft.isChecked)
                numberOfCheckboxes++
            if (checkMiddle.isChecked)
                numberOfCheckboxes++
            if (checkRight.isChecked)
                numberOfCheckboxes++
            intent.putExtra("CHECKBOXES_NUMBER_EXTRA", numberOfCheckboxes.toString()) // put something for them
            startActivityForResult(intent, 1)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // other function
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                val score = data.getStringExtra("SCORE_EXTRA")
                if (score != null)
                    total += score.toInt()
                Toast.makeText(this, total.toString(), Toast.LENGTH_SHORT).show() // when ok
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("SCORE", total.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey("SCORE") && savedInstanceState.getString("SCORE") != null) {
            val totalString = savedInstanceState.getString("SCORE")
            if (totalString != null)
                total = totalString.toInt()
        }
    }


}