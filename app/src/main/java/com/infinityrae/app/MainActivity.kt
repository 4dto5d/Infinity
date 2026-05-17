package com.infinityrae.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InfinityRaeApp() }
    }
}

private val Deep = Color(0xFF120B1E)
private val Violet = Color(0xFF6D4AFF)
private val Rose = Color(0xFFE7A4FF)
private val CardColor = Color(0xFF201531)
private val SoftText = Color(0xFFD7CDE7)

@Composable
fun InfinityRaeApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Deep
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color(0xFF120B1E), Color(0xFF21113A))))
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Header()
                IntakeCard()
                SystemPicker()
                BirthTimePolicyCard()
                CompositeCard()
                FreeLessonsCard()
                ChatCard()
                FooterNote()
            }
        }
    }
}

@Composable
private fun Header() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Infinity Rae", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)
        Text(
            "Human Design, astrology, numerology, composites, and AI-guided synthesis. No paywall in this build.",
            color = SoftText,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun SectionCard(content: @Composable Column.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardColor.copy(alpha = 0.96f)),
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            content()
        }
    }
}

@Composable
private fun IntakeCard() {
    var name by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var birthLocation by remember { mutableStateOf("") }
    var exactTime by remember { mutableStateOf("") }
    var rangeStart by remember { mutableStateOf("") }
    var rangeEnd by remember { mutableStateOf("") }
    var unknownTime by remember { mutableStateOf(false) }

    SectionCard {
        Text("Birth intake", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Text("Supports exact time, known range, or fully unknown birth time.", color = SoftText)
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name or label") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = birthDate, onValueChange = { birthDate = it }, label = { Text("Birth date") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = birthLocation, onValueChange = { birthLocation = it }, label = { Text("Birth location") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = exactTime, onValueChange = { exactTime = it }, label = { Text("Exact birth time, if known") }, modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = unknownTime, onCheckedChange = { unknownTime = it })
            Text("I do not know the birth time", color = SoftText)
        }
        Text("Known range option", color = Rose, fontWeight = FontWeight.SemiBold)
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = rangeStart, onValueChange = { rangeStart = it }, label = { Text("Start") }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(value = rangeEnd, onValueChange = { rangeEnd = it }, label = { Text("End") }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun SystemPicker() {
    var hd by remember { mutableStateOf(true) }
    var astrology by remember { mutableStateOf(true) }
    var numerology by remember { mutableStateOf(true) }
    var hdLevel by remember { mutableStateOf("Beginner") }
    var astroLevel by remember { mutableStateOf("Beginner") }
    var numLevel by remember { mutableStateOf("Beginner") }

    SectionCard {
        Text("Initial questionnaire", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Text("Choose the systems and your current experience level so the reading meets you where you are.", color = SoftText)
        CheckRow("Human Design", hd) { hd = it }
        CheckRow("Astrology", astrology) { astrology = it }
        CheckRow("Numerology", numerology) { numerology = it }
        LevelButtons("Human Design knowledge", hdLevel) { hdLevel = it }
        LevelButtons("Astrology knowledge", astroLevel) { astroLevel = it }
        LevelButtons("Numerology knowledge", numLevel) { numLevel = it }
    }
}

@Composable
private fun CheckRow(label: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onChange)
        Text(label, color = SoftText)
    }
}

@Composable
private fun LevelButtons(title: String, selected: String, onSelect: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, color = Rose, fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Beginner", "Moderate", "Advanced").forEach { level ->
                Button(
                    onClick = { onSelect(level) },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selected == level) Violet else Color(0xFF342448))
                ) { Text(level, fontSize = 12.sp) }
            }
        }
    }
}

@Composable
private fun BirthTimePolicyCard() {
    SectionCard {
        Text("Unknown birth time policy", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Bullet("Exact time: calculate one exact chart.")
        Bullet("Known range: calculate every 5 minutes inside the range.")
        Bullet("Unknown time: calculate every 5 minutes across the full birth day.")
        Bullet("Stable facts are labeled 100% certain when they appear in every sampled chart.")
        Bullet("Variable facts are shown as possible ranges with top likelihood percentages.")
        Bullet("Later paywall idea: lower free precision to 4-hour sampling. Current build: no paywall.")
    }
}

@Composable
private fun CompositeCard() {
    SectionCard {
        Text("Composite readings", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Text("Unlocked in this build:", color = SoftText)
        Bullet("Romantic and counterpart composites")
        Bullet("Sexual chemistry and polarity composites")
        Bullet("Friendship composites")
        Bullet("Career and collaboration composites")
        Bullet("Family composites: parent-child, siblings, chosen family, household dynamics")
        Bullet("Group composites: themes, friction, bridges, roles, and communication loops")
        Bullet("Cross-system synthesis: Human Design vs astrology vs numerology similarities and differences")
    }
}

@Composable
private fun FreeLessonsCard() {
    SectionCard {
        Text("Free lessons", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Bullet("Human Design basics: type, strategy, authority, profile, centers, gates, channels")
        Bullet("Astrology basics: signs, planets, houses, aspects, chart angles")
        Bullet("Numerology basics: life path, expression, soul urge, personal year")
        Bullet("Birth time uncertainty: what changes, what does not, and why ranges matter")
        Bullet("Composite basics: how two or more systems interact without flattening people into labels")
    }
}

@Composable
private fun ChatCard() {
    var prompt by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("Your AI reading draft will appear here. API wiring is intentionally kept separate so no production key is shipped in source code.") }
    SectionCard {
        Text("AI guide", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Text("Ask Infinity Rae to explain, compare, translate jargon, or draft a composite reading.", color = SoftText)
        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            label = { Text("Ask about your chart, composite, family dynamics, or lessons") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
        Button(
            onClick = {
                response = buildDemoResponse(prompt)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Violet)
        ) { Text("Generate guidance draft") }
        Text(response, color = SoftText)
    }
}

private fun buildDemoResponse(prompt: String): String {
    if (prompt.isBlank()) return "Ask a question first. Try: Compare Human Design, astrology, and numerology for a family composite."
    return "Infinity Rae draft: I would answer this by separating facts from interpretation, labeling certainty, then comparing Human Design, astrology, and numerology for overlapping themes and contradictions. Prompt received: $prompt"
}

@Composable
private fun Bullet(text: String) {
    Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()) {
        Text("•", color = Rose, fontSize = 18.sp)
        Spacer(Modifier.width(8.dp))
        Text(text, color = SoftText, fontSize = 14.sp)
    }
}

@Composable
private fun FooterNote() {
    Spacer(Modifier.height(6.dp))
    Text(
        "Current state: free unlocked scaffold. Next step is the deterministic resolver engine for real chart facts.",
        color = SoftText.copy(alpha = 0.72f),
        fontSize = 12.sp
    )
}
