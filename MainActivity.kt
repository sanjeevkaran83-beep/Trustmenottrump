package com.synex.injector

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val bg = Color.rgb(4, 10, 16)
    private val panel = Color.rgb(10, 22, 31)
    private val cyan = Color.rgb(0, 230, 255)
    private val muted = Color.rgb(145, 170, 184)
    private lateinit var root: LinearLayout
    private lateinit var content: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        showHome()
    }

    private fun shape(color: Int, stroke: Int = cyan, radius: Float = 18f): GradientDrawable =
        GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius
            setStroke(dp(1), stroke)
        }

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()

    private fun text(value: String, size: Float, color: Int, bold: Boolean = false): TextView =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            if (bold) typeface = Typeface.create("sans-serif", Typeface.BOLD)
            setPadding(dp(2), dp(3), dp(2), dp(3))
        }

    private fun button(label: String, onClick: () -> Unit): Button =
        Button(this).apply {
            text = label
            textSize = 14f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            background = shape(cyan, cyan, 14f)
            isAllCaps = false
            setPadding(dp(12), dp(8), dp(12), dp(8))
            setOnClickListener { onClick() }
        }

    private fun card(): LinearLayout = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(dp(16), dp(14), dp(16), dp(14))
        background = shape(panel, Color.rgb(0, 110, 135), 18f)
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { bottomMargin = dp(12) }
    }

    private fun setup(title: String, subtitle: String) {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(dp(16), dp(14), dp(16), dp(12))
        }
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(text("S Y N E X", 27f, cyan, true))
            addView(text("INJECTOR  •  FREE FIRE COMPANION", 10f, muted, true))
        }
        root.addView(header)
        val scroller = ScrollView(this)
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, dp(18), 0, dp(12))
        }
        content.addView(text(title, 23f, Color.WHITE, true))
        content.addView(text(subtitle, 13f, muted))
        content.addView(View(this).apply {
            setBackgroundColor(Color.rgb(0, 115, 140))
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(1)).apply {
                topMargin = dp(14); bottomMargin = dp(14)
            }
        })
        scroller.addView(content)
        root.addView(scroller, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
        ))
        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        listOf("⌂ Home", "▶ Launcher", "▤ Scripts", "⚙ Settings").forEach { label ->
            val b = TextView(this).apply {
                text = label
                textSize = 11f
                gravity = Gravity.CENTER
                setTextColor(cyan)
                setPadding(dp(7), dp(12), dp(7), dp(12))
                setOnClickListener {
                    when (label) {
                        "⌂ Home" -> showHome()
                        "▶ Launcher" -> showLauncher()
                        "▤ Scripts" -> showScripts()
                        else -> showSettings()
                    }
                }
            }
            nav.addView(b, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))
        }
        root.addView(nav)
        setContentView(root)
    }

    private fun showHome() {
        setup("Welcome back.", "A clean, neon control center for your game.")
        val c = card()
        c.addView(text("FREE FIRE", 21f, Color.WHITE, true))
        c.addView(text("Game launcher • Status check", 13f, muted))
        c.addView(text("Injection features are disabled in this build.", 12f, cyan))
        c.addView(button("OPEN GAME LAUNCHER") { showLauncher() })
        content.addView(c)
        val shortcuts = card()
        shortcuts.addView(text("QUICK ACCESS", 15f, cyan, true))
        shortcuts.addView(button("SCRIPT MANAGER") { showScripts() })
        shortcuts.addView(button("APP SETTINGS") { showSettings() }.apply {
            layoutParams = LinearLayout.LayoutParams(-1, -2).apply { topMargin = dp(8) }
        })
        content.addView(shortcuts)
        content.addView(text("SYNEX • PLAY SMART • STAY SAFE", 11f, muted))
    }

    private fun showLauncher() {
        setup("Game Launcher", "Launch an installed official Free Fire app.")
        val c = card()
        c.addView(text("FREE FIRE", 22f, Color.WHITE, true))
        c.addView(text("Choose your installed edition. Synex does not modify the game.", 13f, muted))
        c.addView(button("LAUNCH FREE FIRE") { launchGame("com.dts.freefireth") }.apply {
            layoutParams = LinearLayout.LayoutParams(-1, -2).apply { topMargin = dp(14) }
        })
        c.addView(button("LAUNCH FREE FIRE MAX") { launchGame("com.dts.freefiremax") }.apply {
            layoutParams = LinearLayout.LayoutParams(-1, -2).apply { topMargin = dp(8) }
        })
        content.addView(c)
        val status = card()
        status.addView(text("STATUS", 15f, cyan, true))
        status.addView(text("Game availability is checked when you tap Launch.", 13f, muted))
        content.addView(status)
    }

    private fun launchGame(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            Toast.makeText(this, "That Free Fire edition isn't installed.", Toast.LENGTH_LONG).show()
        }
    }

    private fun showScripts() {
        setup("Script Manager", "Local UI demonstration — no code is executed.")
        val c = card()
        c.addView(text("LOCAL FILES", 16f, cyan, true))
        c.addView(text("This starter build does not read, inject, or run scripts. You can add a legitimate text-file viewer later.", 13f, muted))
        c.addView(button("IMPORT (DEMO)") {
            Toast.makeText(this, "File import is a placeholder in this demo.", Toast.LENGTH_SHORT).show()
        }.apply {
            layoutParams = LinearLayout.LayoutParams(-1, -2).apply { topMargin = dp(12) }
        })
        content.addView(c)
    }

    private fun showSettings() {
        setup("Settings", "Personalize your Synex experience.")
        val c = card()
        c.addView(text("APPEARANCE", 16f, cyan, true))
        c.addView(text("Theme: Premium Neon Black + Cyan", 14f, Color.WHITE))
        c.addView(text("Version: 1.0.0 • Android", 13f, muted))
        content.addView(c)
        val about = card()
        about.addView(text("ABOUT & SAFETY", 16f, cyan, true))
        about.addView(text("Synex is a standalone game launcher and UI demo. It does not inject code, bypass protections, or change Free Fire files.", 13f, muted))
        content.addView(about)
    }
}
