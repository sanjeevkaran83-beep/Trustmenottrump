
package com.synex.injector

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val bg = Color.rgb(8, 11, 18)
    private val panel = Color.rgb(18, 24, 35)
    private val cyan = Color.rgb(0, 229, 255)
    private val white = Color.rgb(235, 245, 255)
    private val muted = Color.rgb(140, 160, 180)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        window.decorView.setBackgroundColor(bg)

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 28, 22, 24)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this)
        scroll.addView(page)

        val title = TextView(this).apply {
            text = "SYNEX"
            textSize = 32f
            setTextColor(cyan)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        page.addView(title, match())

        val subtitle = TextView(this).apply {
            text = "INJECTOR  •  ANDROID"
            textSize = 12f
            setTextColor(muted)
            gravity = Gravity.CENTER
        }
        page.addView(subtitle, match())

        val status = TextView(this).apply {
            text = "\n●  READY  |  UI DEMO\n"
            textSize = 14f
            setTextColor(cyan)
            gravity = Gravity.CENTER
        }
        page.addView(status, match())

        addHeading(page, "GAME LAUNCHER")
        addAction(page, "OPEN FREE FIRE", "Launcher demo only. Install the official game to open it.")
        addAction(page, "OPEN FREE FIRE MAX", "Launcher demo only. No game modifications are performed.")

        addHeading(page, "SCRIPT MANAGER")
        val editor = TextView(this).apply {
            text = "-- Synex Script Editor\n-- Paste or type your script here..."
            textSize = 14f
            setTextColor(white)
            setPadding(16, 18, 16, 18)
            setBackgroundColor(panel)
            minHeight = 140
            gravity = Gravity.TOP or Gravity.START
        }
        page.addView(editor, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 150
        ))

        addAction(page, "SELECT SCRIPT FILE", "File picker demo button. File loading is not implemented.")
        addAction(page, "SAVE SCRIPT", "Save is a visual demo and does not write a file.")
        addAction(page, "INJECT  •  DEMO", "Demo only: no injection or game modification is performed.")

        val footer = TextView(this).apply {
            text = "\nSYNEX HUB  •  BLACK / CYAN\nVisual interface build"
            textSize = 11f
            setTextColor(muted)
            gravity = Gravity.CENTER
        }
        page.addView(footer, match())

        setContentView(scroll)
    }

    private fun match() = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    private fun addHeading(parent: LinearLayout, text: String) {
        val heading = TextView(this).apply {
            this.text = "\n$text"
            textSize = 16f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(cyan)
            setPadding(0, 10, 0, 12)
        }
        parent.addView(heading, match())
    }

    private fun addAction(parent: LinearLayout, label: String, message: String) {
        val button = Button(this).apply {
            text = label
            textSize = 13f
            setTextColor(cyan)
            setBackgroundColor(panel)
            isAllCaps = false
            setOnClickListener {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 54
        )
        params.bottomMargin = 10
        parent.addView(button, params)
    }
}
