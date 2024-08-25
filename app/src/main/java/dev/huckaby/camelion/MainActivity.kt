package dev.huckaby.camelion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.huckaby.camelion.databinding.MainBinding
import dev.huckaby.camelion.ui.theme.CamelionTheme
import dev.huckaby.camelion.ui.theme.ThemeVariant
import dev.huckaby.camelion.ui.theme.getThemeVariant
import dev.huckaby.camelion.ui.theme.setThemeVariant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val themeVariant = getThemeVariant()
        when (themeVariant) {
            ThemeVariant.DEFAULT -> setTheme(R.style.Theme_Camelion_Default)
            ThemeVariant.DARK -> setTheme(R.style.Theme_Camelion_Dark)
        }
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.greetings.text = "Hello Android!"
        binding.greetings.setOnClickListener {
            if (themeVariant == ThemeVariant.DEFAULT) {
                setThemeVariant(ThemeVariant.DARK)
            } else {
                setThemeVariant(ThemeVariant.DEFAULT)
            }
            recreate()
        }

        binding.compose.setContent {
            CamelionTheme(
                darkTheme = themeVariant == ThemeVariant.DARK
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CamelionTheme {
        Greeting("Android")
    }
}