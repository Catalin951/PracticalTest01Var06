package ro.pub.cs.systems.eim.practicaltest01var06

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.ceil

class PracticalTest01Var06SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var06_secondary)

        val secondActivityText = findViewById<TextView>(R.id.secondaryActivityText)
        val buttonOk = findViewById<Button>(R.id.buttonOk)

        // Read the string sent from MainActivity
        val leftNumber = intent.getStringExtra("FIRST_EXTRA")
        val middleNumber = intent.getStringExtra("SECOND_EXTRA")
        val rightNumber = intent.getStringExtra("THIRD_EXTRA")
        val joker = "*"
        val checkboxNumberString = intent.getStringExtra("CHECKBOXES_NUMBER_EXTRA")
        var checkboxNumber = 0
        var isGained = false
        if (checkboxNumberString != null)
            checkboxNumber = checkboxNumberString.toInt()

        if (leftNumber == joker &&
            (middleNumber == rightNumber || middleNumber == joker || rightNumber == joker)) {
                secondActivityText.text = "Gained"
            isGained = true
        }

        if (middleNumber == joker &&
            (leftNumber == rightNumber || leftNumber == joker || rightNumber == joker)) {
            secondActivityText.text = "Gained"
            isGained = true
        }

        if (rightNumber == joker &&
            (leftNumber == middleNumber || leftNumber == joker || middleNumber == joker)) {
            secondActivityText.text = "Gained"
            isGained = true
        }

        if (leftNumber == middleNumber && middleNumber == rightNumber) {
            secondActivityText.text = "Gained"
            isGained = true
        }

        var pointsGained = 100
        if (checkboxNumber == 2)
            pointsGained = 10
        else if (checkboxNumber == 1)
            pointsGained = 50
//         else pointsgained = 0

        secondActivityText.text = (secondActivityText.text.toString()) + " " + pointsGained.toString()
//        secondActivityText.text = pointsGained.toString()
//        secondActivityText.text = checkboxNumber.toString()
        // a == joker -> b == c SAU ORICARE E JOKER
        // b == joker -> a == c SAU ORICARE E JOKER
        // c == joker -> a == b SAU ORICARE E JOKER
        // a == b  && b == c
        buttonOk.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var06MainActivity::class.java)
            if (isGained)
                intent.putExtra("SCORE_EXTRA", pointsGained.toString()) // put something for them
            else
                intent.putExtra("SCORE_EXTRA", "0") // put something for them

            setResult(RESULT_OK, intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}