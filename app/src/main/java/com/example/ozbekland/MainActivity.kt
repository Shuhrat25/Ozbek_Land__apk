package com.example.ozbekland

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozbekland.ui.theme.OzbekLandTheme // Используйте вашу тему

class MainActivity : ComponentActivity() {

    // Ключи для SharedPreferences
    private val PREFS_NAME = "O_ZBEK_LAND_PREFS"
    private val KEY_NAME = "USER_NAME"
    private val KEY_AGE = "USER_AGE"
    private val KEY_IS_REGISTERED = "IS_REGISTERED"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // 1. Проверка регистрации при запуске
        if (prefs.getBoolean(KEY_IS_REGISTERED, false)) {
            navigateToHomeScreen()
            return
        }

        setContent {
            OzbekLandTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Вызываем Composable функцию для отображения экрана регистрации
                    RegistrationScreen(
                        modifier = Modifier.padding(innerPadding),
                        prefs = prefs,
                        onRegistrationComplete = { navigateToHomeScreen() }
                    )
                }
            }
        }
    }

    private fun navigateToHomeScreen() {
        // !!! ВАЖНО: Замените HomeActivity::class.java на имя вашего главного экрана
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Закрываем экран регистрации
    }
}

/**
 * Composable функция для экрана регистрации
 */
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    prefs: SharedPreferences,
    onRegistrationComplete: () -> Unit
) {
    val context = LocalContext.current

    // Состояние для поля ввода имени
    var userName by remember { mutableStateOf("") }
    // Состояние для выбранного возраста
    var selectedAge by remember { mutableStateOf<Int?>(null) }

    // Здесь можно настроить цвета, чтобы они соответствовали вашему макету
    val PrimaryGreen = Color(0xFF1E885B) // Пример зеленого цвета
    val BackgroundBlue = Color(0xFFC8E6F0) // Пример голубого фона
    val TextFieldColor = Color(0xFFFFFFFF) // Белый для поля ввода

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundBlue), // Цвет фона из макета
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            // Заголовок
            Text(
                text = "O'ZBEK LAND",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = TextFieldColor,
                modifier = Modifier
                    .background(PrimaryGreen, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Приветствие
            Text(
                text = "Assalomu alaykum!\nKeling boshlaymiz 😊",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Поле ввода имени
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Ismingizni kiriting...") },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,         // рамка при фокусе
                    unfocusedBorderColor = PrimaryGreen,       // рамка без фокуса
                    focusedContainerColor = TextFieldColor,    // фон при фокусе
                    unfocusedContainerColor = TextFieldColor,  // фон без фокуса
                    focusedLabelColor = PrimaryGreen,          // label при фокусе
                    unfocusedLabelColor = PrimaryGreen,        // label без фокуса
                    cursorColor = PrimaryGreen                 // цвет курсора
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Текст "Yoshingizni tanlang"
            Text(
                text = "Yoshingizni tanlang",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Кнопки выбора возраста (6, 7, 8, 9, 10)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                listOf(6, 7, 8, 9, 10).forEach { age ->
                    AgeButton(
                        age = age,
                        isSelected = selectedAge == age,
                        onClick = { selectedAge = age }
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))

            // Кнопка "BOSHLASH"
            Button(
                onClick = {
                    if (userName.isBlank()) {
                        Toast.makeText(context, "Пожалуйста, введите имя!", Toast.LENGTH_SHORT).show()
                    } else if (selectedAge == null) {
                        Toast.makeText(context, "Пожалуйста, выберите возраст!", Toast.LENGTH_SHORT).show()
                    } else {
                        // 4. Сохранение данных
                        val editor = prefs.edit()
                        editor.putString("USER_NAME", userName)
                        editor.putInt("USER_AGE", selectedAge!!)
                        editor.putBoolean("IS_REGISTERED", true)
                        editor.apply()

                        // 5. Переход
                        onRegistrationComplete()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("BOSHLASH", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

/**
 * Composable функция для кнопки возраста (с подсветкой)
 */
@Composable
fun AgeButton(age: Int, isSelected: Boolean, onClick: () -> Unit) {
    val buttonColor = if (isSelected) Color(0xFFFFCC00) else Color(0xFFFFFFFF) // Желтая подсветка
    val textColor = if (isSelected) Color(0xFF1E885B) else Color(0xFF1E885B)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .background(buttonColor, shape = RoundedCornerShape(50)) // Круглый фон
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Text(
            text = age.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}