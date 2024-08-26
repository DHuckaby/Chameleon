package dev.huckaby.chameleon

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import dev.huckaby.chameleon.databinding.MainBinding
import dev.huckaby.chameleon.ui.theme.ThemeVariant
import dev.huckaby.chameleon.ui.theme.getThemeVariant
import dev.huckaby.chameleon.ui.theme.setThemeVariant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val themeVariant = getThemeVariant()
        binding.alternativeIcon.isChecked = themeVariant == ThemeVariant.ALTERNATIVE
        binding.alternativeIcon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setThemeVariant(ThemeVariant.ALTERNATIVE)
            } else {
                setThemeVariant(ThemeVariant.DEFAULT)
            }
            Toast.makeText(
                this,
                "Close application and check out your new icon",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}