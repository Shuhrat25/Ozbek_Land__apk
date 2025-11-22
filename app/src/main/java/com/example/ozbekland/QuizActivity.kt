package com.example.ozbekland

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        QuizState.clear()

        setContent {
            OzbekLandTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    QuizScreen(
                        onQuizFinished = { finish() },
                        onGoHome = { finish() }   // возврат на HomeActivity
                    )
                }
            }
        }
    }
}

@Composable
fun QuizScreen(
    onQuizFinished: () -> Unit,
    onGoHome: () -> Unit
) {
    val questions = remember {
        QuizRepository.questions.shuffled()   // 🔁 перемешиваем один раз
    }
    var currentIndex by remember { mutableStateOf(0) }

    if (currentIndex >= questions.size) {
        // экран "Тугади"
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.fon),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tabriklaymiz!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = onQuizFinished) {
                    Text("Bosh sahifaga qaytish")
                }
            }
        }
        return
    }

    val question = questions[currentIndex]

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        when (question) {
            is Question.InputQuestion -> InputQuestionView(
                question = question,
                onAnswered = { isCorrect ->
                    QuizState.addResult(question, isCorrect)
                    currentIndex++
                },
                onGoHome = onGoHome
            )

            is Question.ChoiceQuestion -> ChoiceQuestionView(
                question = question,
                onAnswered = { isCorrect ->
                    QuizState.addResult(question, isCorrect)
                    currentIndex++
                },
                onGoHome = onGoHome
            )
        }
    }
}


@Composable
fun InputQuestionView(
    question: Question.InputQuestion,
    onAnswered: (Boolean) -> Unit,
    onGoHome: () -> Unit
) {
    // ОЧИСТКА поля при смене вопроса
    var answer by remember(question.id) { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Верх – заголовок
        Spacer(Modifier.height(8.dp))
        Text(
            text = "O'ZBEK LAND",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(24.dp))

        // Карточка с вопросом
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD45B)),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🖼️ КАРТИНКА НАД ВОПРОСОМ, если есть imageResId
                question.imageResId?.let { resId ->
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(12.dp))
                }

                Text(
                    text = question.text,
                    fontSize = 22.sp,
                    color = Color(0xFF064663),
                    lineHeight = 28.sp
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it },
                    placeholder = { Text("Javobingizni kiriting...") },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        val isCorrect = answer.trim()
                            .equals(question.correctAnswer.trim(), ignoreCase = true)
                        onAnswered(isCorrect)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF18A45A),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(text = "Keyingi", fontSize = 18.sp)
                }
            }
        }

        // Это «толкатель» – чтобы нижняя кнопка ушла вниз
        Spacer(modifier = Modifier.weight(1f))

        // НИЖНЯЯ КНОПКА BOSH SAHIFA
        Button(
            onClick = onGoHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF18A45A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(52.dp)
        ) {
            Text(text = "Bosh sahifa", fontSize = 18.sp)
        }

        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun ChoiceQuestionView(
    question: Question.ChoiceQuestion,
    onAnswered: (Boolean) -> Unit,
    onGoHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(8.dp))
        Text(
            text = "O'ZBEK LAND",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1B8)),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🖼 картинка над вопросом (если есть)
                question.imageResId?.let { resId ->
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(12.dp))
                }

                // текст вопроса
                Text(
                    text = question.text,
                    fontSize = 22.sp,
                    color = Color(0xFF064663),
                    lineHeight = 28.sp
                )

                Spacer(Modifier.height(16.dp))

                // 🔹 варианты ответов
                question.options.forEachIndexed { index, option ->

                    // можно чуть-чуть рандомизировать цвета по индексу
                    val color = when (index % 4) {
                        0 -> Color(0xFF70C3FF)
                        1 -> Color(0xFFFFA45B)
                        2 -> Color(0xFF57CC99)
                        else -> Color(0xFFFFD45B)
                    }

                    Button(
                        onClick = {
                            val isCorrect = option.trim()
                                .equals(question.correctAnswer.trim(), ignoreCase = true)
                            onAnswered(isCorrect)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = color,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = option,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        // толкатель, чтобы кнопка ушла вниз
        Spacer(modifier = Modifier.weight(1f))

        // нижняя кнопка Bosh sahifa
        Button(
            onClick = onGoHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF18A45A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(52.dp)
        ) {
            Text(text = "Bosh sahifa", fontSize = 18.sp)
        }

        Spacer(Modifier.height(8.dp))
    }
}
