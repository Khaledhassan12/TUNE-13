package com.music.bitchord.ui.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.music.bitchord.data.settings.AppSettings
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.collect

private const val CONTACT_EMAIL = "Khaled.hassansadik@gmail.com"
private const val CONTACT_PHONE = "+201119523317"
private const val WHATSAPP_URL = "https://wa.me/201119523317"
private const val FACEBOOK_URL = "https://www.facebook.com/Khaledabdulsadiq12"

/**
 * Personal profile, opened only from Settings > Kha Led Hassan.
 *
 * Uses the app's live Material theme and typography, and deliberately contains
 * no duplicated project-origin or third-party ownership notice.
 */
@Composable
fun KhaLedHassanScreen(
    contentPadding: PaddingValues,
    onStart: () -> Unit,
    onScrolled: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val reduceAnimations by AppSettings.reduceAnimation.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    var showDirectActions by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value > 28 }
            .distinctUntilChanged()
            .collect(onScrolled)
    }

    val pageBackground = MaterialTheme.colorScheme.background
    val edgeShade = pageBackground.copy(alpha = 0.86f)
    val heroHeight = (configuration.screenHeightDp.dp * 0.48f).coerceIn(290.dp, 490.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(pageBackground)
            .verticalScroll(scrollState)
            .padding(contentPadding)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(8.dp))

        ProfileReveal(delayMillis = 0, reduceAnimations = reduceAnimations) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heroHeight),
                contentAlignment = Alignment.Center,
            ) {
                // The same portrait is softly diffused behind the clear image.
                // Its face remains unobscured because the sharp layer is drawn
                // above this halo rather than applying blur to the portrait.
                AsyncImage(
                    model = "file:///android_asset/khaledhassan.png",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(30.dp)
                        .graphicsLayer { alpha = 0.34f },
                )
                AsyncImage(
                    model = "file:///android_asset/khaledhassan.png",
                    contentDescription = "Khaled Hassan",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                        .shadow(22.dp, RoundedCornerShape(26.dp), clip = false)
                        .clip(RoundedCornerShape(26.dp)),
                )
                // Soft edge-to-edge shading ties the cover into either theme.
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(edgeShade, Color.Transparent, Color.Transparent, edgeShade),
                            ),
                        ),
                )
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    pageBackground.copy(alpha = 0.10f),
                                    Color.Transparent,
                                    Color.Transparent,
                                    pageBackground.copy(alpha = 0.82f),
                                ),
                            ),
                        ),
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        ProfileReveal(delayMillis = 100, reduceAnimations = reduceAnimations) {
            Text(
                text = "Kha Led Hassan",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
        }

        ProfileReveal(delayMillis = 190, reduceAnimations = reduceAnimations) {
            Text(
                text = "Android Developer  •  Music Producer",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 7.dp),
            )
        }

        ProfileReveal(delayMillis = 280, reduceAnimations = reduceAnimations) {
            Text(
                text = "CONTACT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.6.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 30.dp, bottom = 10.dp),
            )
        }

        ProfileReveal(delayMillis = 350, reduceAnimations = reduceAnimations) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.78f),
                shape = RoundedCornerShape(24.dp),
            ) {
                Column {
                    ContactRow(
                        icon = { Icon(Icons.Rounded.Email, contentDescription = null) },
                        title = "Email",
                        detail = CONTACT_EMAIL,
                        onClick = {
                            launchContactIntent(
                                context,
                                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$CONTACT_EMAIL")),
                            )
                        },
                    )
                    ContactDivider()
                    ContactRow(
                        icon = { Icon(Icons.Rounded.Chat, contentDescription = null) },
                        title = "WhatsApp",
                        detail = CONTACT_PHONE,
                        onClick = {
                            launchContactIntent(context, Intent(Intent.ACTION_VIEW, Uri.parse(WHATSAPP_URL)))
                        },
                    )
                    ContactDivider()
                    ContactRow(
                        icon = { Icon(Icons.Rounded.Facebook, contentDescription = null) },
                        title = "Facebook",
                        detail = "facebook.com/Khaledabdulsadiq12",
                        onClick = {
                            launchContactIntent(context, Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL)))
                        },
                    )
                    ContactDivider()
                    ContactRow(
                        icon = { Icon(Icons.Rounded.AlternateEmail, contentDescription = null) },
                        title = "Direct messages or call",
                        detail = "Message or call me directly",
                        onClick = { showDirectActions = true },
                    )
                }
            }
        }

        ProfileReveal(delayMillis = 460, reduceAnimations = reduceAnimations) {
            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .heightIn(min = 58.dp),
                shape = RoundedCornerShape(30.dp),
            ) {
                Icon(Icons.Rounded.PlayArrow, contentDescription = null)
                Spacer(Modifier.width(9.dp))
                Text(
                    text = "Start Tune 13",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Text(
                text = "Listen to your favorite world",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 28.dp),
            )
        }
    }

    if (showDirectActions) {
        ModalBottomSheet(
            onDismissRequest = { showDirectActions = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 28.dp),
            ) {
                Text(
                    text = "Contact directly",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 14.dp),
                )
                ContactRow(
                    icon = { Icon(Icons.Rounded.Chat, contentDescription = null) },
                    title = "Send a text message",
                    detail = CONTACT_PHONE,
                    onClick = {
                        showDirectActions = false
                        launchContactIntent(
                            context,
                            Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$CONTACT_PHONE")),
                        )
                    },
                )
                ContactDivider()
                ContactRow(
                    icon = { Icon(Icons.Rounded.Call, contentDescription = null) },
                    title = "Call",
                    detail = CONTACT_PHONE,
                    onClick = {
                        showDirectActions = false
                        launchContactIntent(
                            context,
                            Intent(Intent.ACTION_DIAL, Uri.parse("tel:$CONTACT_PHONE")),
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun ContactRow(
    icon: @Composable () -> Unit,
    title: String,
    detail: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            Box(contentAlignment = Alignment.Center) { icon() }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun ContactDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(start = 74.dp),
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.55f),
        thickness = 0.5.dp,
    )
}

@Composable
private fun ProfileReveal(
    delayMillis: Int,
    reduceAnimations: Boolean,
    content: @Composable () -> Unit,
) {
    var started by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { started = true }
    val alpha by animateFloatAsState(
        targetValue = if (started || reduceAnimations) 1f else 0f,
        animationSpec = if (reduceAnimations) tween(0) else tween(620, delayMillis = delayMillis),
        label = "profileRevealAlpha",
    )
    val translateY by animateFloatAsState(
        targetValue = if (started || reduceAnimations) 0f else 18f,
        animationSpec = if (reduceAnimations) tween(0) else tween(620, delayMillis = delayMillis),
        label = "profileRevealTranslation",
    )
    Box(
        modifier = Modifier.graphicsLayer {
            this.alpha = alpha
            translationY = translateY
        },
    ) {
        content()
    }
}

private fun launchContactIntent(context: Context, intent: Intent) {
    try {
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "No compatible app found for this action.", Toast.LENGTH_SHORT).show()
    } catch (_: SecurityException) {
        Toast.makeText(context, "This action is not available on this device.", Toast.LENGTH_SHORT).show()
    }
}
