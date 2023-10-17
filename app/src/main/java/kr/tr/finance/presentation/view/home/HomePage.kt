package kr.tr.finance.presentation.view.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.patrykandpatrick.vico.core.extension.mutableListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kr.tr.finance.R
import kr.tr.finance.common.item.CustomListItem
import kr.tr.finance.common.item.MenuItem
import kr.tr.finance.common.item.NavigationItem
import kr.tr.finance.common.item.SubjectItem
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.model.request.Message
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.presentation.model.MainViewModel
import kr.tr.finance.presentation.navi.homeScreen

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-04
 * Time: 오후 5:40
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val menuClickable = rememberSaveable {
        mutableStateOf(false)
    }

    BackHandler(menuClickable.value) {
        menuClickable.value = false
    }

    val indexCheck = rememberSaveable {
        mutableStateOf(true)
    }

    val snackbarHostState = remember { SnackbarHostState() }

    val navController = rememberNavController()

    val viewModel = hiltViewModel<MainViewModel>()

    val userId = viewModel.userId.observeAsState()


    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier.padding(10.dp),
                containerColor = Color.Gray,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 10.dp
                ),
                shape = CircleShape
            ) {
                AnimatedContent(
                    targetState = indexCheck.value,
                    transitionSpec = {
                        if (targetState) {
                            scaleIn() togetherWith scaleOut()
                        } else {
                            scaleIn() togetherWith scaleOut()
                        }.using(
                            SizeTransform(clip = false)
                        )
                    }, label = ""
                ) {
                    if (it) {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            modifier = Modifier
                                .clickable {
                                    indexCheck.value = indexCheck.value.not()
                                },
                            tint = Color.White,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Close,
                            modifier = Modifier.clickable {
                                indexCheck.value = indexCheck.value.not()
                            },
                            contentDescription = null
                        )
                    }
                }
            }
        },
        containerColor = Color.White.copy(0.2f),
        modifier = Modifier,
        topBar =
        {
            TopAppBar(title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Box(
                        modifier = Modifier
                            .padding(
                                start = 10.dp
                            )
                            .clickable {
                                navController.navigate(NavigationItem.home.route)
                            }
                    ) {
                        Row(

                        ) {

                            Text(
                                text = if (userId.value != null) {
                                    "${userId.value} 님"
                                } else {
                                    "Guest"
                                },
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(10.dp)
                            )

                            Text(
                                text = stringResource(id = R.string.main_home_title),
                                fontSize = 26.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                        }
                    }
                }
            },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Share,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = null
                    )


                    Icon(
                        imageVector = Icons.Default.Settings,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = null
                    )
                },

                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                menuClickable.value = menuClickable.value.not()
                            },
                        tint = colorResource(id = R.color.black),
                    )
                }
            )
        }
    ) {

        MainScreenNavigationConfigurations(
            navController = navController,
            paddingValues = it,
            viewModel = viewModel
        )
        MenuSlider(menuClickable, it, navController)
    }

}

@Composable
fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
) {

    NavHost(
        navController,
        startDestination = NavigationItem.home.route,
    ) {
        homeScreen(navController, paddingValues, viewModel)
    }
}


@Composable
fun ListScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    val scrollState = rememberScrollState()
    val keyWordAge = rememberSaveable {
        mutableIntStateOf(20)
    }
    val keyWordTopDown = rememberSaveable {
        mutableStateOf("이상")
    }
    val keyWordGender = rememberSaveable {
        mutableStateOf("남자")
    }
    val coroutineScope = rememberCoroutineScope()

    var ageExpanded by remember { mutableStateOf(false) }
    var topDownExpanded by remember { mutableStateOf(false) }
    var genderExpanded by remember { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }




    val gpt = GptRequest(
        messages = listOf(
            Message(
                content = "종류 중 하나만 얘기하고 다른 것은 말하지마 ${keyWordAge.value}세 ${keyWordTopDown.value} ${keyWordGender.value} 보험 추천"
            )
        )
    )

    val getInsurance = viewModel.getInsurance(gpt).collectAsLazyPagingItems()


    BoxWithConstraints(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color.Cyan.copy(0.1f)),

        ) {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 8.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 16.dp) // Landscape constraints
        }

        LazyColumn(
            modifier = Modifier

        ) {

            item {
                ConstraintLayout(
                    constraints,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                ) {
                    Card(
                        modifier = Modifier
                            .layoutId("card")
                            .width(120.dp)
                            .height(80.dp)
                            .clickable {
                                ageExpanded = ageExpanded.not()
                            }
                            .padding(16.dp),
                    ) {
                        Text(
                            modifier = Modifier.layoutId("text"),
                            text = "${keyWordAge.value}",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default
                        )
                        DropdownMenu(
                            expanded = ageExpanded,
                            modifier = Modifier
                                .background(Color.White)
                                .layoutId("AgeDropDown"),
                            offset = DpOffset.Zero,
                            onDismissRequest = { ageExpanded = ageExpanded.not() },
                        ) {
                            Text(text = "Age 키")
                            repeat(10) {
                                var age = 18 + it
                                DropdownMenuItem(text = { Text(text = "$age") },
                                    onClick = {
                                        ageExpanded = ageExpanded.not()
                                        keyWordAge.value = age
                                    })
                                Divider(
                                    thickness = 1.dp
                                )
                            }
                        }
                    }




                    Card(
                        modifier = Modifier
                            .layoutId("card1")
                            .width(120.dp)
                            .height(80.dp)
                            .clickable {
                                topDownExpanded = topDownExpanded.not()
                            }
                            .padding(16.dp),
                    ) {
                        Text(
                            modifier = Modifier.layoutId("text"),
                            text = "${keyWordTopDown.value}",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default
                        )
                        DropdownMenu(
                            expanded = topDownExpanded,
                            modifier = Modifier
                                .background(Color.White)
                                .layoutId("AgeDropDown"),
                            offset = DpOffset.Zero,
                            onDismissRequest = { topDownExpanded = topDownExpanded.not() },
                        ) {
                            Text(text = "선택")
                            DropdownMenuItem(text = { Text(text = "이상") },
                                onClick = {

                                    topDownExpanded = topDownExpanded.not()
                                    keyWordTopDown.value = "이상"
                                })
                            Divider(
                                thickness = 1.dp
                            )
                            DropdownMenuItem(text = { Text(text = "미만") },
                                onClick = {

                                    topDownExpanded = topDownExpanded.not()
                                    keyWordTopDown.value = "미만"
                                })
                        }
                    }
                    Card(
                        modifier = Modifier
                            .layoutId("card2")
                            .width(120.dp)
                            .height(80.dp)
                            .clickable {
                                genderExpanded = genderExpanded.not()
                            }
                            .padding(16.dp),
                    ) {
                        Text(
                            modifier = Modifier.layoutId("text"),
                            text = "${keyWordGender.value}",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default
                        )
                        DropdownMenu(
                            expanded = genderExpanded,
                            modifier = Modifier
                                .background(Color.White)
                                .layoutId("AgeDropDown"),
                            offset = DpOffset.Zero,
                            onDismissRequest = { genderExpanded = genderExpanded.not() },
                        ) {
                            Text(text = "성별 선택")
                            DropdownMenuItem(text = { Text(text = "남자") },
                                onClick = {

                                    genderExpanded = genderExpanded.not()
                                    keyWordGender.value = "남자"
                                })
                            Divider(
                                thickness = 1.dp
                            )
                            DropdownMenuItem(text = { Text(text = "여자") },
                                onClick = {

                                    genderExpanded = genderExpanded.not()
                                    keyWordGender.value = "여자"
                                })

                        }
                    }
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                expanded = expanded.not()
                                
                            }
                        },
                        modifier = Modifier.layoutId("button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        )
                    ) {
                        Text(text = "키워드 검색")

                    }
                }

            }

            when (getInsurance.loadState.refresh) {
                LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is LoadState.Error -> {
                    item {

                        Text(text = "에러 ")
                    }
                }

                is LoadState.NotLoading -> {
                    item {


                    }

                }

            }

            items(getInsurance.itemCount) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .wrapContentHeight(),
                    ) {
                        SubjectItem("${viewModel.userId.value ?: "Guest"} 님께 추천하는 보험")
                        Divider()
                        CustomListItem(
                            getInsurance[it]?.cpnyNm ?: "",
                            getInsurance[it]?.kcisGoodNm ?: ""
                        )
                        Divider()
                    }
                }
            }
        }
    }


}


fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        val card = createRefFor("card")
        val card1 = createRefFor("card1")
        val card2 = createRefFor("card2")
        val ageDropDown = createRefFor("AgeDropDown")

        constrain(card) {
            top.linkTo(parent.top, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(card1.start, margin)
        }
        constrain(card1) {
            top.linkTo(parent.top, margin)
            start.linkTo(card.end, margin)
            end.linkTo(card2.start, margin)
        }
        constrain(card2) {
            top.linkTo(parent.top, margin)
            start.linkTo(card1.end, margin)
            end.linkTo(parent.end, margin)
        }
        constrain(text) {
            top.linkTo(card.top, margin)
            start.linkTo(card.start, margin)
        }

        constrain(ageDropDown) {
            top.linkTo(card.bottom)
        }
        constrain(button) {
            top.linkTo(card1.bottom, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
        }
//        constrain(button) {
//            top.linkTo(parent.top, margin = margin)
//        }
//        constrain(text) {
//            top.linkTo(button.bottom, margin)
//        }
    }
}


@Composable
fun MenuSlider(
    menuClickable: MutableState<Boolean>,
    paddingValues: PaddingValues,
    navController: NavHostController,
) {


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxHeight(),
    ) {
        AnimatedVisibility(visible = menuClickable.value,
            enter = slideInHorizontally {
                -it
            },
            exit = slideOutHorizontally {
                -it
            }
        ) {
            MenuItem(menuClickable, navController)
        }
    }
}





