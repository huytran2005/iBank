package com.example.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications

import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R

import com.example.home.ui.theme.Neutral6
import com.example.home.ui.theme.Primary1


@SuppressLint("ContextCastToActivity")
@Composable
fun HomeScreen() {
//    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf("home") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary1)
            .padding(top = 20.dp)
    ) {
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.ic_avata),
                contentDescription = "Avata",
                Modifier.size(50.dp))
            Text(
                text = "User Name",
                color = Neutral6,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .padding(start = 60.dp)
                    .padding(vertical = 25.dp)
            )
            Spacer(Modifier.weight(0.1f))
            Icon(Icons.Filled.Notifications,
                contentDescription = "Notifications",
                Modifier.size(30.dp))
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 50.dp
                    )
                )
                .background(Neutral6),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.padding(12.dp))
            BankCardBox(card)

            val menuItems = listOf(
                MenuItem(painterResource(id= R.drawable.ic_accountandcard), "Account and Card"),
                MenuItem(painterResource(R.drawable.ic_transfer), "Transfer"),
                MenuItem(painterResource(R.drawable.ic_withdraw), "Withdraw"),
                MenuItem(painterResource(R.drawable.ic_mobileprepaid), "Mobile prepaid"),
                MenuItem(painterResource(R.drawable.ic_paythebill), "Pay the bill"),
                MenuItem(painterResource(R.drawable.ic_saveonline), "Save online"),
                MenuItem(painterResource(R.drawable.ic_transactionreport), "Transaction report"),
                MenuItem(painterResource(R.drawable.ic_beneficiary), "Beneficiary")
            )
            MenuGrid(menuItems)
            Spacer(modifier = Modifier.weight(0.1f))
            CustomBottomNavBar(
                items = bottomNavItems,
                selectedRoute = selectedTab,
                onItemSelected = { selectedTab = it })
        }
    }
}
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Filled.Home, "home"),
    BottomNavItem("Search", Icons.Filled.Search, "search"),
    BottomNavItem("Message", Icons.Filled.Email, "message"),
    BottomNavItem("Settings", Icons.Filled.Settings, "settings")
)

@Composable
fun CustomBottomNavBar(
    items: List<BottomNavItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = item.route == selectedRoute

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) Color(0xFF9AD6F4) else Color.Transparent)
                        .clickable { onItemSelected(item.route) }
                        .padding(horizontal = if (isSelected) 16.dp else 0.dp, vertical = 8.dp)
                ) {
                    if (isSelected) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = item.label,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                }
            }
        }
    }
}

@Composable
fun MenuGrid(menuItems: List<MenuItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(menuItems.size) { index ->
            MenuItemBox(menuItems[index])
        }
    }
}

data class MenuItem(
    val image: Painter,
    val label: String
)
@Composable
fun MenuItemBox(item: MenuItem, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .drawBehind {
                // Vẽ bóng ở phía sau
                drawRoundRect(
                    color = Color(0x33000000), // Màu đen nhẹ
                    cornerRadius = CornerRadius(20.dp.toPx(), 20.dp.toPx()),
                    topLeft = Offset(0f, 4f)
                )
            }
    ) {
        // Nội dung có nền trắng
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .clickable { /* handle click */ }
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = item.image,
                contentDescription = item.label,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.label,
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun BankCardBox(
    card: BankCard,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF0B1F71), // Dark Blue
                        Color(0xFF1A52D1)  // Bright Blue
                    )
                )
            )
            .padding(20.dp)

    ) {
        // --- 🔵 Hình tròn trang trí ---
        Canvas(modifier = Modifier
            .size(160.dp)
            .align(Alignment.TopEnd)
            .offset(x = 20.dp, y = (-30).dp)
        ) {
            drawCircle(
                color = Color(0xFF4DA0FF).copy(alpha = 0.3f),
                radius = size.minDimension / 2f
            )
        }

        Canvas(modifier = Modifier
            .size(100.dp)
            .align(Alignment.TopEnd)
            .offset(x = (-10).dp, y = 20.dp)
        ) {
            drawCircle(
                color = Color(0xFF4DA0FF).copy(alpha = 0.6f),
                radius = size.minDimension / 2f
            )
        }

        // --- 📄 Nội dung chính ---
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = card.holderName,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = card.cardType,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = card.cardNumber,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "$${String.format("%.2f", card.balance)}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (card.provider == CardProvider.VISA) {
            Image(
                painter = painterResource(R.drawable.lg_visa),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(50.dp)
            )
        }
    }
}

data class BankCard(
    val holderName: String,
    val cardType: String,
    val cardNumber: String,
    val balance: Double,
    val provider: CardProvider
)

enum class CardProvider {
    VISA,
}

val card = BankCard(
    holderName = "John Smith",
    cardType = "Amazon Platinium",
    cardNumber = "4756 •••• •••• 9018",
    balance = 3469.52,
    provider = CardProvider.VISA
)




@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HomeScreen()
}