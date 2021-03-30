package uz.suhrob.recipeappkmm.androidApp.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import uz.suhrob.recipeappkmm.androidApp.R
import uz.suhrob.recipeappkmm.androidApp.presentation.ui.RecipeAppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            RecipeAppTheme {
                ProvideWindowInsets {
                    Surface {

                    }
                }
            }
        }
    }
}
