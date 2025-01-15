package com.opappdevs.mindfulmoment.ui.view.base.pager

//TODO: adapt for MindfulCard?

//@Composable
//fun Page(
//    state: Pair<Float, Float>, //alpha, scale
//    title: String,
//    content: @Composable () -> Unit
//) {
//    val baseAlpha = remember { .8f }
//    Box(
//        modifier = Modifier
//            .background(Color.Transparent)
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center,
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth(.9f) //dimens
//                .fillMaxHeight(.75f)
//                .alpha(baseAlpha * state.first)
//                .scale(state.second),
//                shape = RoundedCornerShape(20.dp), //define default shape
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 10.dp
//                )
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Text(text = title)
//                content()
//            }
//        }
//    }
//}
//
//@Composable
//@ThemePreviews
//fun PreviewPageView() {
//    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
//        Page(
//            state = Pair(.5f, .875f),
//            title = "Benachrichtigungen"
//        ) {
//            Column() {
//                Text("Möchtest du an deinen täglichen Achtsamkeitsmoment erinnert werden?")
//                MindfulTextButton("Gerne") {
//                }
//            }
//        }
//    }
//}