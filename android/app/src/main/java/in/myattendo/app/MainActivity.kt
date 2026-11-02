package in.myattendo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class SubjectAttendance(
    val code: String, val name: String, val attended: Int, val held: Int
) {
    val percentage: Float get() = if (held == 0) 0f else attended * 100f / held
}

private val demoSubjects = listOf(
    SubjectAttendance("CS301", "Database Management Systems", 28, 32),
    SubjectAttendance("CS302", "Algorithms", 23, 30),
    SubjectAttendance("CS303", "Computer Networks", 18, 28),
    SubjectAttendance("CS304", "Operating Systems", 26, 30)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyAttendoApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAttendoApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val navy = Color(0xFF10182B)
    val violet = Color(0xFF6658D3)
    MaterialTheme(
        colorScheme = lightColorScheme(primary = violet, background = Color(0xFFF5F6FB))
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("MyAttendo", fontWeight = FontWeight.Bold)
                            Text("ADITYA UNIVERSITY · STUDENT", fontSize = 10.sp,
                                color = Color.Gray, letterSpacing = 1.sp)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) { Icon(Icons.Default.NotificationsNone, "Notifications") }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    NavigationBarItem(selected = selectedTab == 0, onClick = { selectedTab = 0 },
                        icon = { Icon(Icons.Default.Dashboard, null) }, label = { Text("Home") })
                    NavigationBarItem(selected = selectedTab == 1, onClick = { selectedTab = 1 },
                        icon = { Icon(Icons.Default.MenuBook, null) }, label = { Text("Subjects") })
                    NavigationBarItem(selected = selectedTab == 2, onClick = { selectedTab = 2 },
                        icon = { Icon(Icons.Default.History, null) }, label = { Text("History") })
                    NavigationBarItem(selected = selectedTab == 3, onClick = { selectedTab = 3 },
                        icon = { Icon(Icons.Default.Person, null) }, label = { Text("Profile") })
                }
            },
            containerColor = Color(0xFFF5F6FB)
        ) { padding ->
            when (selectedTab) {
                0 -> HomeScreen(Modifier.padding(padding), demoSubjects, navy)
                1 -> SubjectsScreen(Modifier.padding(padding), demoSubjects)
                2 -> PlaceholderScreen(Modifier.padding(padding), "Attendance history",
                    "Daily attendance history will appear here after the approved university integration is connected.")
                else -> PlaceholderScreen(Modifier.padding(padding), "Your account",
                    "University SSO profile details will appear here after authentication is configured.")
            }
        }
    }
}

@Composable
private fun HomeScreen(modifier: Modifier, subjects: List<SubjectAttendance>, navy: Color) {
    val attended = subjects.sumOf { it.attended }
    val held = subjects.sumOf { it.held }
    val overall = if (held == 0) 0f else attended * 100f / held
    LazyColumn(modifier.fillMaxSize().padding(horizontal = 18.dp), contentPadding = PaddingValues(vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Text("Good morning 👋", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = navy)
            Text("Here’s your attendance overview.", color = Color.Gray)
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = navy), shape = RoundedCornerShape(22.dp)) {
                Column(Modifier.fillMaxWidth().padding(22.dp)) {
                    Text("OVERALL ATTENDANCE", color = Color(0xFFBFC6D8), fontSize = 11.sp, letterSpacing = 1.3.sp)
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(String.format("%.1f", overall) + "%", color = Color.White,
                            fontSize = 42.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(10.dp))
                        Text("$attended / $held classes", color = Color(0xFFBFC6D8),
                            modifier = Modifier.padding(bottom = 8.dp))
                    }
                    LinearProgressIndicator(progress = { overall / 100f },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = Color(0xFF9A8CFF), trackColor = Color(0xFF39445C))
                    Spacer(Modifier.height(10.dp))
                    Text("Demo preview · Not official attendance", color = Color(0xFFFFD58A), fontSize = 11.sp)
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text("Subject overview", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("View all", color = Color(0xFF6658D3), fontSize = 13.sp)
            }
        }
        items(subjects.take(3)) { SubjectCard(it) }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF4DF))) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, null, tint = Color(0xFF98651A))
                    Spacer(Modifier.width(10.dp))
                    Text("Sample data is shown until Aditya University approves and enables the official integration.",
                        fontSize = 12.sp, color = Color(0xFF684B1B))
                }
            }
        }
    }
}

@Composable
private fun SubjectsScreen(modifier: Modifier, subjects: List<SubjectAttendance>) {
    LazyColumn(modifier.fillMaxSize().padding(horizontal = 18.dp),
        contentPadding = PaddingValues(vertical = 18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("My subjects", fontSize = 25.sp, fontWeight = FontWeight.Bold); Text("Attendance by subject", color = Color.Gray) }
        items(subjects) { SubjectCard(it) }
    }
}

@Composable
private fun SubjectCard(subject: SubjectAttendance) {
    val pct = subject.percentage
    val tone = when { pct >= 75 -> Color(0xFF16865B); pct >= 65 -> Color(0xFFB7791F); else -> Color(0xFFCC4B4B) }
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(18.dp)) {
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(subject.code, color = Color(0xFF6658D3), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Text(subject.name, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                }
                Text(String.format("%.1f%%", pct), color = tone, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            LinearProgressIndicator(progress = { pct / 100f }, modifier = Modifier.fillMaxWidth().height(7.dp),
                color = tone, trackColor = Color(0xFFE9EAF0))
            Spacer(Modifier.height(7.dp))
            Text("${subject.attended} attended · ${subject.held - subject.attended} absent · ${subject.held} held",
                color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
private fun PlaceholderScreen(modifier: Modifier, title: String, message: String) {
    Column(modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.Sync, null, tint = Color(0xFF6658D3), modifier = Modifier.size(52.dp))
        Spacer(Modifier.height(16.dp))
        Text(title, fontWeight = FontWeight.Bold, fontSize = 23.sp)
        Spacer(Modifier.height(8.dp))
        Text(message, color = Color.Gray)
    }
}
