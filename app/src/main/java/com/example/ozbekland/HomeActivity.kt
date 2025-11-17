package com.example.ozbekland

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.ozbekland.ui.theme.OzbekLandTheme

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OzbekLandTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HomeScreen(
                        onStartClick = {
                            startActivity(Intent(this, QuizActivity::class.java))
                        },
                        onOpenQuestionsList = {
                            startActivity(Intent(this, QuestionsListActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun HomeScreen(
    onStartClick: () -> Unit,
    onOpenQuestionsList: () -> Unit
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences(PrefsKeys.PREFS_NAME, Context.MODE_PRIVATE)
    val userName = prefs.getString(PrefsKeys.USER_NAME, "Mehmon") ?: "Mehmon"

    val startButtonColor = Color(0xFF18A45A)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 🔹 Фон
        Image(
            painter = painterResource(id = R.drawable.fon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Основной контент
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // ВЕРХ
            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Start)
            )

            // ЦЕНТР
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "O'ZBEK LAND",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = onStartClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = startButtonColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(56.dp)
                ) {
                    Text(text = "Boshlash", fontSize = 20.sp)
                }
            }

            // НИЖНЕЕ МЕНЮ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomMenuButton(
                    icon = Icons.Filled.Home,
                    label = "Bosh sahifa",
                    onClick = { /* мы уже на главной, можно оставить пустым */ }
                )

                BottomMenuButton(
                    icon = Icons.Filled.List,
                    label = "Savollar",
                    onClick = onOpenQuestionsList
                )
            }
        }
    }
}

@Composable
fun BottomMenuButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFFFFFBE6),
        shadowElevation = 4.dp,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFFFA824C)
            )
            Spacer(Modifier.width(8.dp))
            Text(text = label, color = Color(0xFF064663), fontSize = 14.sp)
        }
    }
}
