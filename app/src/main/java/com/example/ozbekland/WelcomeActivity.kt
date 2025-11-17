package com.example.ozbekland

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.ozbekland.ui.theme.OzbekLandTheme

// ---------- UI первого экрана ----------

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Проверяем – уже есть имя и возраст?
        val prefs = getSharedPreferences(PrefsKeys.PREFS_NAME, Context.MODE_PRIVATE)
        val hasUser = prefs.contains(PrefsKeys.USER_NAME) &&
                prefs.contains(PrefsKeys.USER_AGE)

        if (hasUser) {
            // Уже заполнял – сразу на главный экран
            openHome()
            return
        }

        // 2. Иначе показываем экран ввода
        setContent {
            OzbekLandTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    WelcomeScreen(
                        onFinished = { name, age ->
                            // Сохраняем данные
                            prefs.edit()
                                .putString(PrefsKeys.USER_NAME, name)
                                .putInt(PrefsKeys.USER_AGE, age)
                                .apply()

                            // Переходим на главный экран
                            openHome()
                        }
                    )
                }
            }
        }
    }

    private fun openHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // чтобы нельзя было вернуться назад
    }
}

@Composable
fun WelcomeScreen(
    onFinished: (name: String, age: Int) -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var selectedAge by remember { mutableStateOf<Int?>(null) }
    var showError by remember { mutableStateOf(false) }

    // Цвета
    val startButtonColor = Color(0xFF18A45A)
    val ageNormal = Color(0xFFFFE27A)
    val ageSelected = Color(0xFFFFC94D)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 🔹 ФОНОВАЯ КАРТИНКА
        Image(
            painter = painterResource(id = R.drawable.fon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Весь контент поверх фона
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(Modifier.height(24.dp))

            Text(
                text = "O'ZBEK LAND",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Assalomu alaykum!\nKeling boshlaymiz 🙂",
                fontSize = 20.sp,
                color = Color(0xFF064663)
            )

            Spacer(Modifier.height(32.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Ismingizni kiriting...") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f),
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Yoshingizni tanlang",
                fontSize = 18.sp,
                color = Color(0xFF064663)
            )

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val ages = listOf(6, 7, 8, 9, 10)
                ages.forEach { age ->
                    AgeButton(
                        age = age,
                        isSelected = selectedAge == age,
                        normalColor = ageNormal,
                        selectedColor = ageSelected,
                        onClick = { selectedAge = age }
                    )
                    Spacer(Modifier.width(6.dp))
                }
            }

            if (showError) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Ism va yoshni kiriting",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    val trimmedName = name.text.trim()
                    if (trimmedName.isEmpty() || selectedAge == null) {
                        showError = true
                    } else {
                        showError = false
                        onFinished(trimmedName, selectedAge!!)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = startButtonColor,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(56.dp)
            ) {
                Text(text = "BOSHLASH", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun AgeButton(
    age: Int,
    isSelected: Boolean,
    normalColor: Color,
    selectedColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) selectedColor else normalColor,
            contentColor = Color(0xFF064663)
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        modifier = Modifier
            .defaultMinSize(minWidth = 40.dp)
    ) {
        Text(text = age.toString(), fontSize = 18.sp)
    }
}
