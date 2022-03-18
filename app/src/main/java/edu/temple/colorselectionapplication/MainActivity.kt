package edu.temple.colorselectionapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), SelectionFragment.SelectionFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container1, SelectionFragment.newInstance(getColors()))
            .commit()

    }

    fun getColors(): Array<String> = arrayOf(
        "Red",
        "Black",
        "Green",
        "Blue",
        "Yellow",
        "White",
        "Magenta",
        "Grey",
        "Teal",
        "Lime",
        "Maroon",
        "Navy"
    )

    override fun colorSelected() {
        if (findViewById<View>(R.id.container2) == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container1, DisplayFragment())
                .addToBackStack(null)
                .commit()
    }
}