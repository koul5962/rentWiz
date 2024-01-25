package com.rentwiz.app.compose.ui

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rentwiz.app.R
import com.rentwiz.app.compose.model.PersonData
import com.rentwiz.app.compose.navigation.BottomBarScreen
import com.rentwiz.app.compose.navigation.navgraph.BottomNavGraph
import com.rentwiz.app.ui.theme.Shapes

@Preview
@Composable
fun RecompositionExample() {
    var myValue by remember { mutableStateOf(false) }
    Log.d("Recomposition","Try")

    Surface(color = MaterialTheme.colorScheme.background) {
        Button(onClick = { myValue = !myValue }) {
            Text(text = "$myValue")
            Log.d("Recomposition","Button Content Lambda")
        }
    }
}

@Preview
@Composable
fun ColumnExample() {
    var myValue by remember { mutableStateOf("") }
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = myValue,
                onValueChange = { myValue = it },
                label = { Text(text = "Name") }
            )
            Text(text = myValue)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ExpandableCardExample() {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if(expandedState) 180f else 0f, label = "Rotate",
    )
    Surface(color = MaterialTheme.colorScheme.background) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = Shapes.medium,
            onClick = {
                expandedState = !expandedState
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(6f),
                        text = "My Title",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .rotate(rotationState),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop-Down-Arrow"
                        )
                    }
                }
                if(expandedState) {
                    Text(
                        text = stringResource(id = R.string.lorem_ipsum),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GoogleButtonExample() {
    var clicked by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.surface,
        onClick = {
            clicked = !clicked
        },
        shape = Shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),

        ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = com.rentwiz.app.coreui.R.drawable.google),
                contentDescription = "Google Button",
                tint = Color.Unspecified,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier
                .width(8.dp))
            if(!clicked) Text(text = "Sign up with Google") else Text(text = "Creating account...")
            Spacer(modifier = Modifier
                .width(10.dp))
            if(clicked) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun CoilExample() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            contentAlignment = Alignment.Center
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://source.unsplash.com/user/c_v_r/1900×800")
                    .size(Size.ORIGINAL)
                    .build()
            )
            Image(painter = painter, contentDescription = "Logo image")
            if(painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview
@Composable
fun LazyColumnExample() {
    val persons = PersonData()
    LazyRow {
        items(items = persons.getAllData()) {
            Row {
                Text(text = "${it.age}")
                Text(text = it.name)
            }
        }
    }
}

@Preview
@Composable
fun Temp() {
    CustomIndicator()
}
@Composable
fun CustomIndicator(
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    canvasSize: Dp = 300.dp,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 50f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth: Float = 50f,
    smallText: String = "Remaining",
    smallTextColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
    smallTextFontSize: TextUnit = MaterialTheme.typography.headlineMedium.fontSize,
    bigTextSuffix: String = "GB",
    bigTextFontSize: TextUnit = MaterialTheme.typography.headlineLarge.fontSize,
    bigTextColor: Color = MaterialTheme.colorScheme.onSurface
) {
    var allowedIndicatorValue by remember { mutableIntStateOf(maxIndicatorValue) }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember { mutableFloatStateOf(0f) }
        LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage =
        (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000), label = ""
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000), label = ""
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0)
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        else
            bigTextColor,
        animationSpec = tween(1000), label = ""
    )


    Surface {
        Column(
            modifier = Modifier
                .size(canvasSize)
                .padding(20.dp)
                .drawBehind {
                    drawArc(
                        size = size,
                        color = backgroundIndicatorColor,
                        startAngle = 150f,
                        sweepAngle = 240f,
                        useCenter = false,
                        style = Stroke(
                            width = backgroundIndicatorStrokeWidth,
                            cap = StrokeCap.Round
                        ),
                    )
                    drawArc(
                        size = size,
                        color = foregroundIndicatorColor,
                        startAngle = 150f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(
                            width = foregroundIndicatorStrokeWidth,
                            cap = StrokeCap.Round
                        ),
                    )
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = smallText,
                color = smallTextColor,
                fontSize = smallTextFontSize,
                textAlign = TextAlign.Center
            )
            Text(
                text = "$receivedValue ${bigTextSuffix.take(2)}",
                color = animatedBigTextColor,
                fontSize = bigTextFontSize,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CustomUiExample() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var indicatorValue by remember { mutableIntStateOf(0) }
        CustomIndicator(
            indicatorValue = indicatorValue
        )
        OutlinedTextField(
            value = indicatorValue.toString(), onValueChange = {
                indicatorValue = if(it.isNotEmpty()) {
                    it.toInt()
                } else {
                    0
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

    }
}

@Composable
fun BottomNavExample() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        topBar = {
            TopBar {

            }
        }
    ) {
        println(it)
        BottomNavGraph(navHostController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Details,
        BottomBarScreen.Login
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

   NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Home"
            )
        },
        actions = {
            IconButton(
                onClick = {
                    onSearchClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = ""
                )
            }
        }

    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shadowElevation = 3.dp,
        color = MaterialTheme.colorScheme.primary
    ) {
    }
}

