package uz.suhrob.recipeappkmm.androidApp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.imageloading.ImageLoadState
import uz.suhrob.recipeappkmm.androidApp.R
import uz.suhrob.recipeappkmm.androidApp.presentation.ui.Gray500
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
    ) {
        Column(modifier = Modifier.clickable { onClick() }) {
            CoilImage(data = recipe.image ?: "") { imageState ->
                if (imageState is ImageLoadState.Success) {
                    Image(
                        modifier = Modifier.aspectRatio(16f / 9),
                        painter = imageState.painter,
                        contentDescription = recipe.title + " image",
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Image(
                        modifier = Modifier.aspectRatio(16f / 9),
                        bitmap = ImageBitmap.imageResource(id = R.drawable.recipe_placeholder),
                        contentDescription = "Recipe placeholder image",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    recipe.title,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Column {
                    Spacer(modifier = Modifier.height(8.dp))

                    recipe.readyInMinutes?.let { time ->
                        RecipeInfo(
                            icon = ImageVector.vectorResource(id = R.drawable.ic_clock),
                            text = "$time mins"
                        )
                    }

                    recipe.cuisines.firstOrNull()?.let { cuisine ->
                        RecipeInfo(
                            icon = ImageVector.vectorResource(id = R.drawable.ic_chef_hat),
                            text = cuisine
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeInfo(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(12.dp),
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text,
            style = MaterialTheme.typography.caption.copy(color = Gray500)
        )
    }
}
