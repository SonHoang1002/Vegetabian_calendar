package com.hts.vegetabiancalendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hts.vegetabiancalendar.model.ModelVerseDhammapada
import com.hts.vegetabiancalendar.view_model.MyViewModel

@Composable
fun CDhammapadaList(viewModel: MyViewModel) {
    val dhammapadaData = viewModel.dhammapadaData

    if (dhammapadaData == null) {
        // Loading state
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Hiển thị danh sách các bài kệ
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            dhammapadaData.chapter.forEach { chapter ->
                item {
                    Text(
                        text = chapter.chapterTitle,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(chapter.verses.size) { idx ->
                    VerseItem(chapter.verses[idx])
                }
            }
        }
    }
}

@Composable
fun VerseItem(verse: ModelVerseDhammapada) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = "Verse ${verse.verseNumber}",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        verse.text.forEach { line ->
            Text(
                text = line,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
