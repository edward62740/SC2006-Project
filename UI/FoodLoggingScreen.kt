@Composable
fun FoodLogging(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
                .requiredWidth(width = 360.dp)
                .requiredHeight(height = 803.dp)
                .background(color = Color(0xff0b2615))
        ) {
        Text(
            text = "Today",
            color = Color.White,
            style = TextStyle(
                        fontSize = 16.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 5.dp,
                                    y = 76.dp)
                        .requiredWidth(width = 60.dp)
                        .requiredHeight(height = 15.dp))
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Vector",
            modifier = Modifier
                        .fillMaxSize())
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Vector",
            modifier = Modifier
                        .fillMaxSize()
                        .rotate(degrees = -180f))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 0.dp,
                                    y = 113.dp)
                        .requiredWidth(width = 342.dp)
                        .requiredHeight(height = 105.dp)
                        .clip(shape = RoundedCornerShape(11.dp))
                        .background(color = Color.White))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 18.dp,
                                    y = 143.115966796875.dp)
                        .requiredWidth(width = 324.dp)
                        .requiredHeight(height = 23.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color(0xffe6e6e6)))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 12.dp,
                                    y = 142.dp)
                        .requiredWidth(width = 271.dp)
                        .requiredHeight(height = 23.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color(0xff08b347)))
        Text(
            text = "Calorie left",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-105.5).dp,
                                    y = 122.dp)
                        .requiredWidth(width = 113.dp)
                        .requiredHeight(height = 15.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 310.dp,
                                    y = 121.dp)
            ) {
            Text(
                text = "75%",
                color = Color.Black,
                style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold))
            }
        Text(
            text = "_",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-105.5).dp,
                                    y = 170.dp)
                        .requiredWidth(width = 17.dp)
                        .requiredHeight(height = 15.dp))
        Text(
            text = "+",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-7.5).dp,
                                    y = 173.dp)
                        .requiredWidth(width = 33.dp)
                        .requiredHeight(height = 15.dp))
        Text(
            text = "=",
            color = Color(0xff3b3b3b),
            style = TextStyle(
                        fontSize = 12.sp),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 101.5.dp,
                                    y = 173.dp)
                        .requiredWidth(width = 33.dp)
                        .requiredHeight(height = 15.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 12.dp,
                                    y = 173.dp)
                        .requiredWidth(width = 34.dp)
                        .requiredHeight(height = 29.dp)
            ) {
            Text(
                text = "1,750",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 34.dp)
                                .requiredHeight(height = 15.dp))
            Text(
                text = "Goal",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 14.dp)
                                .requiredWidth(width = 34.dp)
                                .requiredHeight(height = 15.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 103.dp,
                                    y = 173.dp)
                        .requiredWidth(width = 33.dp)
                        .requiredHeight(height = 29.dp)
            ) {
            Text(
                text = "1500",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 33.dp)
                                .requiredHeight(height = 15.dp))
            Text(
                text = "Food",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 14.dp)
                                .requiredWidth(width = 33.dp)
                                .requiredHeight(height = 15.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 209.dp,
                                    y = 173.dp)
                        .requiredWidth(width = 50.dp)
                        .requiredHeight(height = 29.dp)
            ) {
            Text(
                text = "500",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 30.dp)
                                .requiredHeight(height = 15.dp))
            Text(
                text = "Exercise",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 14.dp)
                                .requiredWidth(width = 50.dp)
                                .requiredHeight(height = 15.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 280.dp,
                                    y = 172.dp)
                        .requiredWidth(width = 67.dp)
                        .requiredHeight(height = 29.dp)
            ) {
            Text(
                text = "750",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 29.dp)
                                .requiredHeight(height = 15.dp))
            Text(
                text = "Remaining",
                color = Color(0xff3b3b3b),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = 0.dp,
                                                y = 14.dp)
                                .requiredWidth(width = 67.dp)
                                .requiredHeight(height = 15.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 5.dp,
                                    y = 230.dp)
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
                text = "0 of 500 Cal",
                color = Color.White,
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 40.dp,
                                                y = 22.dp)
                                .requiredWidth(width = 76.dp)
                                .requiredHeight(height = 12.dp))
            Text(
                text = "Breakfast",
                color = Color.White,
                style = TextStyle(
                                fontSize = 16.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 40.dp,
                                                y = 7.dp)
                                .requiredWidth(width = 112.dp)
                                .requiredHeight(height = 15.dp))
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Vector",
                modifier = Modifier
                                .fillMaxSize())
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
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 9.dp,
                                    y = 564.dp)
                        .requiredWidth(width = 342.dp)
                        .requiredHeight(height = 89.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 342.dp)
                                .requiredHeight(height = 89.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(x = 0.dp,
                                                            y = 0.dp)
                                        .requiredWidth(width = 342.dp)
                                        .requiredHeight(height = 89.dp)
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
                    text = "Dinner",
                    color = Color.White,
                    style = TextStyle(
                                        fontSize = 16.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 40.dp,
                                                            y = 7.dp)
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "0 of 500 Cal",
                    color = Color.White,
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 40.dp,
                                                            y = 22.dp)
                                        .requiredWidth(width = 76.dp)
                                        .requiredHeight(height = 12.dp))
                Text(
                    text = "Reminder to have Dinner",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(x = (-1).dp,
                                                            y = 60.dp)
                                        .requiredWidth(width = 144.dp)
                                        .requiredHeight(height = 12.dp))
                Image(
                    painter = painterResource(id = R.drawable.zondiconsaddsolid),
                    contentDescription = "zondicons:add-solid",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 300.dp,
                                                            y = 12.dp)
                                        .requiredSize(size = 22.dp))
                }
            Image(
                painter = painterResource(id = R.drawable.seatlunchsvgrepocom1),
                contentDescription = "seat-lunch-svgrepo-com 1",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 4.dp,
                                                y = 7.dp)
                                .requiredSize(size = 33.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 9.dp,
                                    y = 456.dp)
                        .requiredWidth(width = 342.dp)
                        .requiredHeight(height = 89.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 342.dp)
                                .requiredHeight(height = 89.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(x = 0.dp,
                                                            y = 0.dp)
                                        .requiredWidth(width = 342.dp)
                                        .requiredHeight(height = 89.dp)
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
                    text = "Lunch",
                    color = Color.White,
                    style = TextStyle(
                                        fontSize = 16.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 40.dp,
                                                            y = 7.dp)
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "0 of 500 Cal",
                    color = Color.White,
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 40.dp,
                                                            y = 22.dp)
                                        .requiredWidth(width = 76.dp)
                                        .requiredHeight(height = 12.dp))
                Text(
                    text = "Reminder to have Lunch",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(x = (-1).dp,
                                                            y = 60.dp)
                                        .requiredWidth(width = 144.dp)
                                        .requiredHeight(height = 12.dp))
                Image(
                    painter = painterResource(id = R.drawable.zondiconsaddsolid),
                    contentDescription = "zondicons:add-solid",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 300.dp,
                                                            y = 12.dp)
                                        .requiredSize(size = 22.dp))
                }
            Image(
                painter = painterResource(id = R.drawable.group),
                contentDescription = "Group",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 4.dp,
                                                y = 7.dp)
                                .requiredWidth(width = 34.dp)
                                .requiredHeight(height = 27.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 9.dp,
                                    y = 672.dp)
                        .requiredWidth(width = 342.dp)
                        .requiredHeight(height = 89.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 342.dp)
                                .requiredHeight(height = 89.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(x = 0.dp,
                                                            y = 0.dp)
                                        .requiredWidth(width = 342.dp)
                                        .requiredHeight(height = 89.dp)
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
                    text = "Snacks",
                    color = Color.White,
                    style = TextStyle(
                                        fontSize = 16.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 40.dp,
                                                            y = 4.dp)
                                        .requiredWidth(width = 112.dp)
                                        .requiredHeight(height = 15.dp))
                Text(
                    text = "0 of 500 Cal",
                    color = Color.White,
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 40.dp,
                                                            y = 22.dp)
                                        .requiredWidth(width = 76.dp)
                                        .requiredHeight(height = 12.dp))
                Text(
                    text = "Reminder to have some healthy snacks",
                    color = Color(0xff3b3b3b),
                    style = TextStyle(
                                        fontSize = 12.sp),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(x = 0.dp,
                                                            y = 60.dp)
                                        .requiredWidth(width = 238.dp)
                                        .requiredHeight(height = 12.dp))
                Image(
                    painter = painterResource(id = R.drawable.zondiconsaddsolid),
                    contentDescription = "zondicons:add-solid",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 300.dp,
                                                            y = 12.dp)
                                        .requiredSize(size = 22.dp))
                }
            Image(
                painter = painterResource(id = R.drawable.sandwichfoodsvgrepocom1),
                contentDescription = "sandwich-food-svgrepo-com 1",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 7.dp,
                                                y = 7.dp)
                                .requiredSize(size = 27.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 9.dp,
                                    y = 794.dp)
                        .requiredWidth(width = 160.dp)
                        .requiredHeight(height = 33.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color(0xff051f17)))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 191.dp,
                                    y = 794.dp)
                        .requiredWidth(width = 160.dp)
                        .requiredHeight(height = 33.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color(0xff051f17)))
        Text(
            text = "Nutrition",
            color = Color.White,
            style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-91).dp,
                                    y = 802.dp)
                        .requiredWidth(width = 70.dp)
                        .requiredHeight(height = 12.dp))
        Text(
            text = "Notes",
            color = Color.White,
            style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold),
            modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 91.5.dp,
                                    y = 802.dp)
                        .requiredWidth(width = 47.dp)
                        .requiredHeight(height = 12.dp))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                                    y = (-7).dp)
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 42.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 360.dp)
                                .requiredHeight(height = 42.dp)
                                .background(color = Color(0xff0b2615)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 16.dp,
                                                y = 12.dp)
                                .requiredWidth(width = 54.dp)
                                .requiredHeight(height = 18.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .requiredWidth(width = 54.dp)
                                        .requiredHeight(height = 18.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_513pm),
                    contentDescription = "5:13 PM",
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 4.dp,
                                                            y = 4.dp)
                                        .requiredWidth(width = 46.dp)
                                        .requiredHeight(height = 10.dp))
                }
            Image(
                painter = painterResource(id = R.drawable.alarm),
                contentDescription = "Alarm",
                colorFilter = ColorFilter.tint(Color(0xff222227)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 242.dp,
                                                y = 12.dp)
                                .requiredSize(size = 18.dp))
            Image(
                painter = painterResource(id = R.drawable.signal),
                contentDescription = "Signal",
                colorFilter = ColorFilter.tint(Color(0xff222227)),
                modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 305.dp,
                                                end = 37.dp,
                                                top = 5.dp,
                                                bottom = 19.dp))
            Image(
                painter = painterResource(id = R.drawable.wifi),
                contentDescription = "Wifi",
                colorFilter = ColorFilter.tint(Color(0xff222227)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 284.dp,
                                                y = 12.dp)
                                .requiredSize(size = 18.dp))
            Image(
                painter = painterResource(id = R.drawable.bluetooth),
                contentDescription = "Bluetooth",
                colorFilter = ColorFilter.tint(Color(0xff222227)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 263.dp,
                                                y = 12.dp)
                                .requiredSize(size = 18.dp))
            Image(
                painter = painterResource(id = R.drawable.battery),
                contentDescription = "Battery",
                colorFilter = ColorFilter.tint(Color(0xff222227)),
                modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 326.dp,
                                                end = 16.dp,
                                                top = 5.dp,
                                                bottom = 19.dp))
            }
        Image(
            painter = painterResource(id = R.drawable.bifilter),
            contentDescription = "bi:filter",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 324.dp,
                                    y = 18.dp)
                        .requiredSize(size = 23.dp))
        Text(
            text = "Diary",
            color = Color.White,
            style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 38.dp,
                                    y = 18.dp))
        Image(
            painter = painterResource(id = R.drawable.makiarrow),
            contentDescription = "maki:arrow",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 14.dp,
                                    y = 18.dp)
                        .requiredSize(size = 20.dp)
                        .rotate(degrees = -180f))
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                                    y = 747.dp)
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 57.dp)
            ) {
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.BottomStart)
                                .offset(x = 0.dp,
                                                y = (-1).dp)
                                .requiredWidth(width = 360.dp)
                                .requiredHeight(height = 56.dp)
                                .background(color = Color(0xff08b347)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(x = (-1).dp,
                                                y = 4.dp)
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
                                                y = 14.dp)
                                .requiredSize(size = 34.dp))
            Image(
                painter = painterResource(id = R.drawable.icbaselinebook),
                contentDescription = "ic:baseline-book",
                colorFilter = ColorFilter.tint(Color(0xff051f17)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 86.dp,
                                                y = 19.dp)
                                .requiredSize(size = 25.dp))
            Image(
                painter = painterResource(id = R.drawable.basilexploresolid),
                contentDescription = "basil:explore-solid",
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 233.dp,
                                                y = 14.dp)
                                .requiredSize(size = 34.dp))
            Image(
                painter = painterResource(id = R.drawable.solarhamburgermenubold),
                contentDescription = "solar:hamburger-menu-bold",
                colorFilter = ColorFilter.tint(Color(0xff051f17)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 308.dp,
                                                y = 18.dp)
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
                                                y = 40.dp))
            Text(
                text = "Dairy",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 83.dp,
                                                y = 40.dp))
            Text(
                text = "Explore",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 228.dp,
                                                y = 42.dp))
            Text(
                text = "More",
                color = Color(0xff051f17),
                style = TextStyle(
                                fontSize = 12.sp),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 307.dp,
                                                y = 41.dp))
            }
        }
 }

@Preview(widthDp = 360, heightDp = 803)
@Composable
private fun FoodLoggingPreview() {
    FoodLogging(Modifier)
 }