@Composable
fun HomeCalories(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
                .requiredWidth(width = 359.dp)
                .requiredHeight(height = 803.dp)
                .background(color = Color(0xff051f17))
        ) {
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 8.dp,
                                    y = 569.dp)
                        .requiredWidth(width = 342.dp)
                        .requiredHeight(height = 205.dp)
            ) {
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 342.dp)
                                .requiredHeight(height = 205.dp)
                                .clip(shape = RoundedCornerShape(11.dp))
                                .background(color = Color.White))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 342.dp)
                                .requiredHeight(height = 42.dp)
                                .clip(shape = RoundedCornerShape(topStart = 11.dp, topEnd = 11.dp))
                                .background(color = Color(0xff051f17)))
            Text(
                text = "History",
                color = Color.White,
                style = TextStyle(
                                fontSize = 16.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 17.dp,
                                                y = 12.dp)
                                .requiredWidth(width = 183.dp)
                                .requiredHeight(height = 30.dp))
            Image(
                painter = painterResource(id = R.drawable.zondiconsaddsolid),
                contentDescription = "zondicons:add-solid",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 303.dp,
                                                y = 10.dp)
                                .requiredSize(size = 22.dp))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 20.dp,
                                                y = 53.dp)
                                .requiredWidth(width = 304.dp)
                                .requiredHeight(height = 33.dp)
                ) {
                Text(
                    text = "Large Size Egg",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "273 cals",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 238.dp,
                                                            y = 9.dp)
                                        .requiredWidth(width = 50.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "3 eggs",
                    color = Color(0xff666666),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 1.dp,
                                                            y = 18.dp)
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Image(
                    painter = painterResource(id = R.drawable.rimore2line),
                    contentDescription = "ri:more-2-line",
                    colorFilter = ColorFilter.tint(Color(0xff3b3b3b)),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 288.dp,
                                                            y = 9.dp)
                                        .requiredSize(size = 16.dp))
                }
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 19.dp,
                                                y = 103.dp)
                                .requiredWidth(width = 305.dp)
                                .requiredHeight(height = 34.dp)
                ) {
                Text(
                    text = "Chicken Breast",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "273 cals",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 239.dp,
                                                            y = 12.dp)
                                        .requiredWidth(width = 49.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "100 g",
                    color = Color(0xff666666),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 2.dp,
                                                            y = 19.dp)
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Image(
                    painter = painterResource(id = R.drawable.rimore2line),
                    contentDescription = "ri:more-2-line",
                    colorFilter = ColorFilter.tint(Color(0xff3b3b3b)),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 289.dp,
                                                            y = 12.dp)
                                        .requiredSize(size = 16.dp))
                }
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 20.dp,
                                                y = 154.dp)
                                .requiredWidth(width = 304.dp)
                                .requiredHeight(height = 34.dp)
                ) {
                Text(
                    text = "Blueberries",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "92 cals",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 244.dp,
                                                            y = 12.dp)
                                        .requiredWidth(width = 43.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "100 g",
                    color = Color(0xff666666),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 2.dp,
                                                            y = 19.dp)
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Image(
                    painter = painterResource(id = R.drawable.rimore2line),
                    contentDescription = "ri:more-2-line",
                    colorFilter = ColorFilter.tint(Color(0xff3b3b3b)),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 288.dp,
                                                            y = 12.dp)
                                        .requiredSize(size = 16.dp))
                }
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 0.dp,
                                    y = 52.356689453125.dp)
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 232.dp)
                        .clip(shape = RoundedCornerShape(19.dp))
                        .background(brush = Brush.linearGradient(
                    0f to Color(0xfff0f0ff), 
1f to Color.White,
                    start = Offset(172.5f, 0f),
                    end = Offset(172.5f, 232f))))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 19.dp,
                                    y = 94.356689453125.dp)
                        .requiredSize(size = 160.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredSize(size = 160.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffe6e6e6)))
            Box(
                modifier = Modifier
                                .requiredSize(size = 160.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffe6e6e6)))
            Box(
                modifier = Modifier
                                .requiredSize(size = 160.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xff08b347)))
            }
        Text(
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
    withStyle(style = SpanStyle(
        color = Color(0xff3b3b3b),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold)) {append("1500\n")}
    withStyle(style = SpanStyle(
        color = Color(0xff3b3b3b),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold)) {append("Remaining")}},
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-80.5).dp,
                                    y = 152.356689453125.dp))
        Text(
            text = "1500",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 259.dp,
                                    y = 132.356689453125.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 243.dp,
                                    y = 134.356689453125.dp)
                        .requiredSize(size = 12.dp)
                        .background(color = Color(0xffe6e6e6)))
        Text(
            text = "Base Goal",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 243.dp,
                                    y = 118.356689453125.dp))
        Text(
            text = "1500",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 259.dp,
                                    y = 196.356689453125.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 243.dp,
                                    y = 198.356689453125.dp)
                        .requiredSize(size = 12.dp)
                        .background(color = Color(0xff08b347)))
        Text(
            text = "Consumed",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 243.dp,
                                    y = 182.356689453125.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 7.dp,
                                    y = 308.dp)
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 138.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White))
        Text(
            text = "Daily Water Intake",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 118.dp,
                                    y = 408.dp))
        Text(
            text = "64 . oz",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 158.dp,
                                    y = 396.dp))
        Image(
            painter = painterResource(id = R.drawable.group10),
            contentDescription = "Group 10",
            colorFilter = ColorFilter.tint(Color(0xff08b347)),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 161.dp,
                                    y = 330.dp)
                        .requiredWidth(width = 34.dp)
                        .requiredHeight(height = 56.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 302.dp,
                                    y = 361.dp)
                        .requiredSize(size = 29.dp)
            ) {
            Box(
                modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = CircleShape)
                                .background(color = Color(0xff08b347)))
            Image(
                painter = painterResource(id = R.drawable.group),
                contentDescription = "Group",
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 2.416748046875.dp,
                                                y = 2.416748046875.dp)
                                .requiredSize(size = 24.dp))
            }
        Text(
            text = "Calories",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.dp,
                                    y = 68.356689453125.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 26.dp,
                                    y = 362.496826171875.dp)
                        .requiredSize(size = 22.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffddecff)))
        Image(
            painter = painterResource(id = R.drawable.group13),
            contentDescription = "Group 13",
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.9306640625.dp,
                                    y = 363.357421875.dp)
                        .requiredWidth(width = 24.dp)
                        .requiredHeight(height = 24.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 5.dp,
                                    y = 470.dp)
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 87.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White))
        Text(
            text = "70 kg",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 98.dp,
                                    y = 495.dp))
        Text(
            text = "You have a healthy BMI",
            color = Color(0xff999999),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 99.dp,
                                    y = 518.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 13.dp,
                                    y = 483.dp)
                        .requiredSize(size = 62.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffe6e6e6)))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 13.dp,
                                    y = 483.dp)
                        .requiredSize(size = 62.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff051f17)))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 293.dp,
                                    y = 493.dp)
                        .requiredSize(size = 42.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredSize(size = 42.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = CircleShape)
                                        .background(color = Color(0xff08b347)))
                Image(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = "Group",
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 3.5.dp,
                                                            y = 3.5.dp)
                                        .requiredSize(size = 35.dp))
                }
            }
        Image(
            painter = painterResource(id = R.drawable.group),
            contentDescription = "Group",
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 29.916748046875.dp,
                                    y = 502.694091796875.dp)
                        .requiredWidth(width = 27.dp)
                        .requiredHeight(height = 27.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 7.dp,
                                    y = 907.dp)
                        .requiredWidth(width = 165.dp)
                        .requiredHeight(height = 127.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 187.dp,
                                    y = 907.dp)
                        .requiredWidth(width = 165.dp)
                        .requiredHeight(height = 127.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White))
        Text(
            text = "Friends",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 19.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 55.dp,
                                    y = 983.dp))
        Text(
            text = "Community",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 19.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 216.dp,
                                    y = 983.dp))
        Text(
            text = "Your support squad",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 34.dp,
                                    y = 1002.dp))
        Text(
            text = "Food and fitness info",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 210.dp,
                                    y = 1004.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 69.dp,
                                    y = 938.dp)
                        .requiredSize(size = 42.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffddebff)))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 249.dp,
                                    y = 938.dp)
                        .requiredSize(size = 42.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffddebff)))
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Vector",
            modifier = Modifier
                        .fillMaxSize())
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 7.dp,
                                    y = 1047.dp)
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 111.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 153.dp,
                                    y = 1064.dp)
                        .requiredSize(size = 45.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffbfc5fd)))
        Image(
            painter = painterResource(id = R.drawable.iconparksolidmoreapp),
            contentDescription = "icon-park-solid:more-app",
            colorFilter = ColorFilter.tint(Color(0xff0066ee)),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 160.6416015625.dp,
                                    y = 1071.6414794921875.dp)
                        .requiredSize(size = 31.dp))
        Text(
            text = "Sync up!",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 19.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 0.5.dp,
                                    y = 1110.dp))
        Text(
            text = "Connect 35+ health and fitness partners",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 1.dp,
                                    y = 1131.dp))
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Vector",
            modifier = Modifier
                        .fillMaxSize())
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = (-1).dp,
                                    y = 746.dp)
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 56.dp)
            ) {
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.BottomStart)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 360.dp)
                                .requiredHeight(height = 56.dp)
                                .background(color = Color(0xff08b347)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = (-1).dp,
                                                y = 1.dp)
                                .requiredSize(size = 48.dp)
                ) {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "Vector",
                    modifier = Modifier
                                        .fillMaxSize())
                Box(
                    modifier = Modifier
                                        .fillMaxSize()
                                        .blur(radius = 20.dp,
                                                            edgeTreatment = BlurredEdgeTreatment.Unbounded)
                                        .background(color = Color.Black))
                }
            Image(
                painter = painterResource(id = R.drawable.icroundhome),
                contentDescription = "ic:round-home",
                colorFilter = ColorFilter.tint(Color(0xff051f17)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 14.dp,
                                                y = 11.dp)
                                .requiredSize(size = 34.dp))
            Image(
                painter = painterResource(id = R.drawable.icbaselinebook),
                contentDescription = "ic:baseline-book",
                colorFilter = ColorFilter.tint(Color(0xff051f17)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 86.dp,
                                                y = 16.dp)
                                .requiredSize(size = 25.dp))
            Image(
                painter = painterResource(id = R.drawable.basilexploresolid),
                contentDescription = "basil:explore-solid",
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 233.dp,
                                                y = 11.dp)
                                .requiredSize(size = 34.dp))
            Image(
                painter = painterResource(id = R.drawable.solarhamburgermenubold),
                contentDescription = "solar:hamburger-menu-bold",
                colorFilter = ColorFilter.tint(Color(0xff051f17)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 308.dp,
                                                y = 15.dp)
                                .requiredWidth(width = 28.dp)
                                .requiredHeight(height = 27.dp))
            Text(
                text = "Home",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 14.dp,
                                                y = 37.dp))
            Text(
                text = "Dairy",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 83.dp,
                                                y = 37.dp))
            Text(
                text = "Explore",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 228.dp,
                                                y = 39.dp))
            Text(
                text = "More",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 307.dp,
                                                y = 38.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 11.dp,
                                    y = 16.356689453125.dp)
                        .requiredWidth(width = 54.dp)
                        .requiredHeight(height = 17.dp)
            ) {
            Text(
                text = "Today",
                color = Color.White,
                style = TextStyle(
                                fontSize = 14.sp))
            Image(
                painter = painterResource(id = R.drawable.iconarrowdown),
                contentDescription = "icon \"arrow down\"",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 43.84716796875.dp,
                                                y = 6.1416015625.dp)
                                .requiredWidth(width = 10.dp)
                                .requiredHeight(height = 5.dp))
            }
        Image(
            painter = painterResource(id = R.drawable.iconnotification),
            contentDescription = "icon \"notification\"",
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 331.dp,
                                    y = 18.dp)
                        .requiredWidth(width = 16.dp)
                        .requiredHeight(height = 20.dp))
        }
 }

@Preview(widthDp = 359, heightDp = 803)
@Composable
private fun HomeCaloriesPreview() {
    HomeCalories(Modifier)
 }