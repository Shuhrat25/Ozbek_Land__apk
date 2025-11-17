package com.example.ozbekland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.ozbekland.ui.theme.OzbekLandTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List



class QuestionsListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OzbekLandTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    QuestionsListScreen(
                        onBack = { finish() },
                        onGoHome = { finish() }   // вернуться на главную
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionsListScreen(
    onBack: () -> Unit,
    onGoHome: () -> Unit
) {
    val questions = QuizRepository.questions

    val resultsSnapshot = remember { QuizState.results.toList() }
    val resultMap = remember(resultsSnapshot) {
        resultsSnapshot.associateBy({ it.questionId }, { it.isCorrect })
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Верхняя панель
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Orqaga",
                        tint = Color.White
                    )
                }

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Savollar",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(16.dp))

            // Список – даём ему weight, чтобы снизу осталось место для меню
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(questions) { q ->
                    val isCorrect = resultMap[q.id]

                    val bgColor = when (isCorrect) {
                        true -> Color(0xFF57CC99)   // yashil
                        false -> Color(0xFFFF6B6B)  // qizil
                        null -> Color(0xFFFFF1B8)   // hali javob berilmagan
                    }

                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = bgColor),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = q.text,
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // 🔹 Нижнее меню, как на главном экране
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
                    onClick = onGoHome
                )

                BottomMenuButton(
                    icon = Icons.Filled.List,
                    label = "Savollar",
                    onClick = { /* уже на этом экране, можно ничего не делать */ }
                )
            }
        }
    }
}