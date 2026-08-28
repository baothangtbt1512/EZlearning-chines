package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText

private const val MAX_NAME_LENGTH = 30

@Composable
fun EditNameDialog(
  currentName: String,
  currentAvatar: String = "dudu",
  onConfirm: (name: String, avatar: String) -> Unit,
  onDismiss: () -> Unit
) {
  var nameInput by remember { mutableStateOf(currentName) }
  var selectedAvatar by remember { mutableStateOf(currentAvatar) }
  val keyboardController = LocalSoftwareKeyboardController.current

  val trimmed = nameInput.trim()
  val isValid = trimmed.isNotEmpty() && trimmed.length <= MAX_NAME_LENGTH

  Dialog(onDismissRequest = onDismiss) {
    Card(
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .testTag("edit_name_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "✏️ Hồ sơ & Bạn đồng hành",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
          text = "Tùy chỉnh tên và nhân vật đồng hành cùng bạn!",
          fontSize = 13.sp,
          color = SecondaryText,
          lineHeight = 18.sp,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Avatar Selection Row
        Text(
          text = "Chọn bạn đồng hành:",
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = DarkText,
          modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // Dudu Option
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(14.dp))
              .background(if (selectedAvatar == "dudu") Color(0xFFF0FDF4) else Color(0xFFF8FAFC))
              .border(
                if (selectedAvatar == "dudu") 2.dp else 1.dp,
                if (selectedAvatar == "dudu") PrimaryJade else Color(0xFFE2E8F0),
                RoundedCornerShape(14.dp)
              )
              .clickable { selectedAvatar = "dudu" }
              .padding(8.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              DuduVector(size = 52.dp, pose = CharacterPose.STANDING_HAPPY)
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = "Dudu (Gấu nâu)",
                fontSize = 12.sp,
                fontWeight = if (selectedAvatar == "dudu") FontWeight.Bold else FontWeight.Medium,
                color = if (selectedAvatar == "dudu") DarkJade else DarkText
              )
            }
          }

          // Bubu Option
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(14.dp))
              .background(if (selectedAvatar == "bubu") Color(0xFFF0FDF4) else Color(0xFFF8FAFC))
              .border(
                if (selectedAvatar == "bubu") 2.dp else 1.dp,
                if (selectedAvatar == "bubu") PrimaryJade else Color(0xFFE2E8F0),
                RoundedCornerShape(14.dp)
              )
              .clickable { selectedAvatar = "bubu" }
              .padding(8.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              BubuVector(size = 52.dp, pose = CharacterPose.STANDING_HAPPY)
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = "Bubu (Gấu trúc)",
                fontSize = 12.sp,
                fontWeight = if (selectedAvatar == "bubu") FontWeight.Bold else FontWeight.Medium,
                color = if (selectedAvatar == "bubu") DarkJade else DarkText
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Name Input
        Text(
          text = "Tên hiển thị của bạn:",
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = DarkText,
          modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
          value = nameInput,
          onValueChange = { newValue ->
            if (newValue.length <= MAX_NAME_LENGTH) {
              nameInput = newValue
            }
          },
          placeholder = { Text("Nhập tên mới...", color = Color(0xFFA0AEC0)) },
          singleLine = true,
          keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done
          ),
          keyboardActions = KeyboardActions(
            onDone = {
              if (isValid) {
                keyboardController?.hide()
                onConfirm(trimmed, selectedAvatar)
              }
            }
          ),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryJade,
            unfocusedBorderColor = Color(0xFFCBD5E0),
            focusedTextColor = DarkText,
            unfocusedTextColor = DarkText
          ),
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("edit_name_input")
        )

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, end = 4.dp),
          horizontalArrangement = Arrangement.End
        ) {
          Text(
            text = "${nameInput.length}/$MAX_NAME_LENGTH",
            fontSize = 12.sp,
            color = if (nameInput.length >= MAX_NAME_LENGTH) Color(0xFFE53E3E) else Color(0xFF718096)
          )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = onDismiss,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f)
          ) {
            Text("Hủy", color = Color(0xFF4A5568))
          }

          Button(
            onClick = {
              if (isValid) {
                keyboardController?.hide()
                onConfirm(trimmed, selectedAvatar)
              }
            },
            enabled = isValid,
            colors = ButtonDefaults.buttonColors(
              containerColor = PrimaryJade,
              disabledContainerColor = Color(0xFFCBD5E0)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f)
          ) {
            Text("Lưu", fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}
